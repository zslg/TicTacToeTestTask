
SET search_path = public, pg_catalog;

CREATE SEQUENCE game_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


CREATE TABLE game (
    id bigint DEFAULT nextval('game_id_seq'::regclass) NOT NULL,
    name character varying(512) NOT NULL,
    status character varying(50) NOT NULL
);

CREATE SEQUENCE game_step_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE game_step (
    id bigint DEFAULT nextval('game_step_id_seq'::regclass) NOT NULL,
    game_id bigint NOT NULL,
    step_type smallint NOT NULL,
    row_ smallint NOT NULL,
    column_ smallint NOT NULL,
    create_date timestamp with time zone DEFAULT now() NOT NULL
);


ALTER TABLE ONLY game
    ADD CONSTRAINT game_pkey PRIMARY KEY (id);

ALTER TABLE ONLY game_step
    ADD CONSTRAINT game_step_pkey PRIMARY KEY (id);


ALTER TABLE ONLY game_step
    ADD CONSTRAINT game_step_fk FOREIGN KEY (game_id) REFERENCES game(id);