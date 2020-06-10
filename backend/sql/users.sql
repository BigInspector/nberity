CREATE TABLE public."user"
(
    user_id serial NOT NULL,
    username character(20) NOT NULL,
    password text NOT NULL,
    PRIMARY KEY (user_id)
);

ALTER TABLE public."user"
    OWNER to postgres;