CREATE TABLE user_table (
        id bigserial,
        surname character varying(255),
        username character varying(255),
        date DATE,
        email character varying(255),
        password character varying(255),
        CONSTRAINT user_table_pkey PRIMARY KEY (id),
        CONSTRAINT email_key UNIQUE(email)
        );
