package ru.otus.java.basic.homeworks;

public class Application {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        System.out.println("Привет мир!!!");

        /*
        CREATE TABLE public.tests (
        test_gid serial4 NOT NULL,
        subject varchar(100) NOT NULL,
        text varchar(100) NOT NULL,
        min_score_points int NOT NULL,
        CONSTRAINT test_gid_pk PRIMARY KEY (test_gid)
        )

        CREATE TABLE public.questions (
        test_gid serial4 NOT NULL,
        question_gid serial4 NOT NULL,
        number int NOT NULL,
        text varchar(100) NOT NULL,
        CONSTRAINT question_gid_pk PRIMARY KEY (question_gid),
        FOREIGN KEY (test_gid) REFERENCES tests (test_gid)
        )

        CREATE TABLE public.answers (
        question_gid serial4 NOT NULL,
        answer_gid serial4 NOT NULL,
        number int NOT NULL,
        text varchar(100) NOT NULL,
        correct boolean NOT NULL,
        score_points int,
        CONSTRAINT answer_gid_pk PRIMARY KEY (answer_gid),
        FOREIGN KEY (question_gid) REFERENCES questions (question_gid)
        )

        CREATE TABLE public.users (
        user_gid serial4 NOT NULL,
        "name" varchar(100) NOT NULL,
        age int NOT NULL,
        post varchar(100),
        CONSTRAINT user_gid_pk PRIMARY KEY (user_gid)
        )

        CREATE TABLE public.user_answers (
        user_gid serial4 NOT NULL,
        answer_gid serial4 NOT NULL,
        CONSTRAINT user_answer_gid_pk PRIMARY KEY (user_gid, answer_gid),
        FOREIGN KEY (user_gid) REFERENCES users (user_gid),
        FOREIGN KEY (answer_gid) REFERENCES answers (answer_gid)
        )

        CREATE TABLE public.user_tests (
        user_gid serial4 NOT NULL,
        test_gid serial4 NOT NULL,
        passed int NOT NULL,
        CONSTRAINT user_test_gid_pk PRIMARY KEY (user_gid, test_gid),
        FOREIGN KEY (user_gid) REFERENCES users (user_gid),
        FOREIGN KEY (test_gid) REFERENCES tests (test_gid)
        )


--ананимный блок для проверки прошел ли указанные тест указанный юзер и фиксирование прохождения/не прохождения в таблице user_test
DO $$
DECLARE
v_passed INT;
v_count INT;
--параметры гид_юзер и гид_тест для проверки, проверяем прошел ли указанные тест указанный юзер
--если прошел записываем 1 в таблицу user_tests  в поле passed, иначе 0
p_user_gid INT = 1;
p_test_gid INT = 1;

BEGIN
        --Проверяем сумма набранных баллов за указанный тест у указанного пользователя больше прохожного балла по указаномму тесту
		SELECT
		CASE WHEN sum(a.score_points) > MAX(t.min_score_points) THEN 1 ELSE 0 END AS passed
		INTO v_passed
		FROM public.user_answers ua
			JOIN public.users u on  u.user_gid = ua.user_gid
			JOIN public.answers a on a.answer_gid = ua.answer_gid
			JOIN public.questions q on q.question_gid = a.question_gid
			JOIN public.tests t on t.test_gid = q.test_gid
		WHERE u.user_gid = p_user_gid
			AND q.test_gid = p_test_gid
		GROUP BY u.user_gid;

        -- Проверяем были данные ответы у указаного пользователя по указанному тесту
		IF v_passed IS NOT NULL THEN
            -- Если ответы есть, то проверяем фиксировался ли ранее результат у пользователя по тесту
			SELECT COUNT(*)
			INTO v_count
			FROM public.user_tests
			WHERE user_gid = p_user_gid
     		AND test_gid = p_test_gid;
            --Если ранее не фиксировался добавляем строк
			IF v_count = 0 THEN
				INSERT INTO public.user_tests
		        (user_gid, test_gid, passed)
		        VALUES (p_user_gid, p_test_gid, v_passed );
			ELSE
                --Если ранее фиксировался обновляем строку
				UPDATE public.user_tests
				SET passed = v_passed
				WHERE user_gid = p_user_gid
				    AND test_gid = p_test_gid;
			END IF;
			COMMIT;

        END IF;
	end $$;

        **/
    }
}
