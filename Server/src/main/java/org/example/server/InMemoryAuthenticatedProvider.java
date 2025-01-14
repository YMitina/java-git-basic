package org.example.server;


import java.sql.*;


public class InMemoryAuthenticatedProvider implements AuthenticatedProvider {

    private Server server;
    private Connection connection;

    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERS_QUERY = "select uc.username from public.users_chat uc where uc.login = ? and uc.\"password\" = ?";
    private static final String ROLE_ID_QUERY = "select rc.role_id from public.roles_chat rc where rc.title = ? ";

    private static final String LOGIN_ADMIN ="""
                    select count(*) from public.users_chat uc
					join public.users_roles_chat urc on urc.login = uc.login
					join public.roles_chat rc on urc.role_id = rc.role_id and rc.title = 'ADMIN'
					where uc.login = ? 
                    """;
    private static final String USERNAME_ADMIN = """
					select count(*) from public.users_chat uc
					join public.users_roles_chat urc on urc.login = uc.login
					join public.roles_chat rc on urc.role_id = rc.role_id and rc.title = 'ADMIN'
					where uc.username = ? 
                    """;
    private static final String LOGIN_EXIST = "select count(*) as username from public.users_chat uc where uc.login = ? ";
    private static final String USERNAME_EXIST = "select count(*) as username from public.users_chat uc where uc.username = ? ";
    private static final String INSERT_USER = "insert into  public.users_chat (login, \"password\", username) values (?, ?, ?)";
    private static final String INSERT_USER_ROLE = "insert into  public.users_roles_chat  (login, role_id) values (?, ?)";


    public InMemoryAuthenticatedProvider(Server server) {
        this.server = server;
    }

    @Override
    public void initialize() {
        try {
            this.connection = DriverManager.getConnection(DATABASE_URL, "postgres", "admin");
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
        PreparedStatement preparedStatement = this.connection.prepareStatement(LOGIN_ADMIN);
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


            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, username);
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement2 = connection.prepareStatement(ROLE_ID_QUERY);
            preparedStatement2.setString(1, "USER");

            int id_role = 0;
            try (ResultSet resultSet = preparedStatement2.executeQuery()) {
                while (resultSet.next()) {
                    id_role = resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

            PreparedStatement preparedStatement3 = connection.prepareStatement(INSERT_USER_ROLE);
            preparedStatement3.setString(1, login);
            preparedStatement3.setInt(2, id_role);
            preparedStatement3.executeUpdate();

        } catch (
                SQLException e) {
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
