-- public.auth_sessions definition

-- Drop table

-- DROP TABLE public.auth_sessions;

CREATE TABLE public.auth_sessions (
	id uuid NOT NULL,
	user_id uuid NOT NULL,
	access_token text NOT NULL,
	refresh_token text NULL,
	created_at timestamp NOT NULL,
	expires_at timestamp NOT NULL,
	active bool NOT NULL,
	CONSTRAINT auth_sessions_pkey PRIMARY KEY (id)
);
