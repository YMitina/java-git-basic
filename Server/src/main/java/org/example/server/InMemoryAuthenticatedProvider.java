package org.example.server;


import java.sql.*;


public class InMemoryAuthenticatedProvider implements AuthenticatedProvider {

    private Server server;
    private Connection connection;
    private Statement statement;

    //
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";

    private static final String USERS_QUERY = "select max(uc.username) as username from public.users_chat uc where uc.login = ? and uc.\"password\" = ?";
    private static final String USERS_ADMIN ="""
                    select count(*) from public.users_chat uc where uc.login = ? and uc.\"role\" = 'ADMIN'
                    """;
    private static final String USERNAME_ADMIN = """
                    select count(*) from public.users_chat uc where uc.username = ? and uc.\"role\" = 'ADMIN'
                    """;
    private static final String LOGIN_EXIST = "select count(*) as username from public.users_chat uc where uc.login = ? ";
    private static final String USERNAME_EXIST = "select count(*) as username from public.users_chat uc where uc.username = ? ";
    private static final String INSERT_USER = "insert into  public.users_chat (login, \"password\", username, \"role\") values (?, ?, ?, ?)";

    public InMemoryAuthenticatedProvider(Server server) {
        this.server = server;
    }

    @Override
    public void initialize() {
        try {
            this.connection = DriverManager.getConnection(DATABASE_URL, "postgres", "admin");
            this.statement = this.connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("Инициализация InMemoryAuthenticatedProvider");
    }

    private String getUsernameByLoginAndPassword(String login, String password) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(USERS_QUERY);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getString(1) != null) {
                        return resultSet.getString(1);
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    private boolean getIsAdmin(String login) throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(USERS_ADMIN);
        preparedStatement.setString(1, login);

        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                if (resultSet.getInt(1) != 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean authenticate(ClientHandler clientHandler, String login, String password) {
        String authUsername = getUsernameByLoginAndPassword(login, password);
        if (authUsername == null) {
            clientHandler.sendMsg("Неверный логин/пароль");
            return false;
        }
        if (server.isUsernameBusy(authUsername)) {
            clientHandler.sendMsg("Указанная учетная запись уже занята");
            return false;
        }
        clientHandler.setUsername(authUsername);
        server.subscribe(clientHandler);
        clientHandler.sendMsg("/authok " + authUsername);

        return true;
    }

    private boolean isLoginAlreadyExists(String login) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(LOGIN_EXIST);
            preparedStatement.setString(1, login);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getInt(1) == 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    private boolean isUsernameAlreadyExists(String username) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(USERNAME_EXIST);
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getInt(1) == 1) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean registration(ClientHandler clientHandler, String login, String password, String username) {
        if (login.length() < 3 || password.length() < 3 || username.length() < 3) {
            clientHandler.sendMsg("Логин 3+ символа, пароль 3+ символа, имя пользователя 3+ символа");
            return false;
        }
        if (isLoginAlreadyExists(login)) {
            clientHandler.sendMsg("Указанный логин уже занят");
            return false;
        }
        if (isUsernameAlreadyExists(username)) {
            clientHandler.sendMsg("Указанное имя пользователя уже занято");
            return false;
        }

        try {
            connection.setAutoCommit(false);
            // создаём пользователя
            PreparedStatement ps = connection.prepareStatement(INSERT_USER);
            ps.setString(1, login);
            ps.setString(2, password);
            ps.setString(3, username);
            ps.setString(4, "USER");
            ps.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        clientHandler.setUsername(username);
        server.subscribe(clientHandler);
        clientHandler.sendMsg("/regok " + username);
        return true;
    }

    public boolean isAdmin(String username) {

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(USERNAME_ADMIN);
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    if (resultSet.getInt(1) != 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
