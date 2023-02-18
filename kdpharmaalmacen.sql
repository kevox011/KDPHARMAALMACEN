--
-- PostgreSQL database dump
--

-- Dumped from database version 15.0
-- Dumped by pg_dump version 15.0

-- Started on 2023-02-17 19:00:44

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 16399)
-- Name: producto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.producto (
    codigoproducto bigint NOT NULL,
    agotandose boolean,
    nombreproducto character varying(20),
    observaciones character varying(50),
    preciounitario numeric(38,0),
    stock integer
);


ALTER TABLE public.producto OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16405)
-- Name: registroentrada; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.registroentrada (
    codigoentrada integer NOT NULL,
    cantidad integer,
    codigoguiaremision character varying(20),
    costototal numeric(38,0),
    fechaentrada date,
    fechavencimiento date,
    observaciones character varying(50),
    porcaducar boolean,
    realizausuario bigint,
    registraproducto bigint
);


ALTER TABLE public.registroentrada OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16404)
-- Name: registroentrada_codigoentrada_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.registroentrada_codigoentrada_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.registroentrada_codigoentrada_seq OWNER TO postgres;

--
-- TOC entry 3351 (class 0 OID 0)
-- Dependencies: 215
-- Name: registroentrada_codigoentrada_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.registroentrada_codigoentrada_seq OWNED BY public.registroentrada.codigoentrada;


--
-- TOC entry 218 (class 1259 OID 16412)
-- Name: registrosalida; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.registrosalida (
    codigosalida integer NOT NULL,
    cantidad integer,
    cliente character varying(50),
    costototal numeric(38,0),
    documenteocliente character varying(50),
    fechasalida date,
    observaciones character varying(50),
    tipodocumentocliente boolean,
    realizausuario bigint,
    registraproducto bigint
);


ALTER TABLE public.registrosalida OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16411)
-- Name: registrosalida_codigosalida_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.registrosalida_codigosalida_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.registrosalida_codigosalida_seq OWNER TO postgres;

--
-- TOC entry 3352 (class 0 OID 0)
-- Dependencies: 217
-- Name: registrosalida_codigosalida_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.registrosalida_codigosalida_seq OWNED BY public.registrosalida.codigosalida;


--
-- TOC entry 220 (class 1259 OID 16419)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.usuario (
    codigousuario integer NOT NULL,
    apellidomaterno character varying(10),
    apellidopaterno character varying(10),
    "contraseña" character varying(15),
    nombreusuario character varying(10),
    nombres character varying(20),
    tipodeusuario boolean
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16418)
-- Name: usuario_codigousuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.usuario_codigousuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuario_codigousuario_seq OWNER TO postgres;

--
-- TOC entry 3353 (class 0 OID 0)
-- Dependencies: 219
-- Name: usuario_codigousuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.usuario_codigousuario_seq OWNED BY public.usuario.codigousuario;


--
-- TOC entry 3187 (class 2604 OID 16408)
-- Name: registroentrada codigoentrada; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registroentrada ALTER COLUMN codigoentrada SET DEFAULT nextval('public.registroentrada_codigoentrada_seq'::regclass);


--
-- TOC entry 3188 (class 2604 OID 16415)
-- Name: registrosalida codigosalida; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registrosalida ALTER COLUMN codigosalida SET DEFAULT nextval('public.registrosalida_codigosalida_seq'::regclass);


--
-- TOC entry 3189 (class 2604 OID 16422)
-- Name: usuario codigousuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario ALTER COLUMN codigousuario SET DEFAULT nextval('public.usuario_codigousuario_seq'::regclass);


--
-- TOC entry 3191 (class 2606 OID 16403)
-- Name: producto producto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (codigoproducto);


--
-- TOC entry 3193 (class 2606 OID 16410)
-- Name: registroentrada registroentrada_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registroentrada
    ADD CONSTRAINT registroentrada_pkey PRIMARY KEY (codigoentrada);


--
-- TOC entry 3195 (class 2606 OID 16417)
-- Name: registrosalida registrosalida_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registrosalida
    ADD CONSTRAINT registrosalida_pkey PRIMARY KEY (codigosalida);


--
-- TOC entry 3197 (class 2606 OID 16426)
-- Name: usuario usuario_nombreusuario_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_nombreusuario_key UNIQUE (nombreusuario);


--
-- TOC entry 3199 (class 2606 OID 16424)
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (codigousuario);


--
-- TOC entry 3200 (class 2606 OID 16427)
-- Name: registroentrada fk_registroentrada_realizausuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registroentrada
    ADD CONSTRAINT fk_registroentrada_realizausuario FOREIGN KEY (realizausuario) REFERENCES public.usuario(codigousuario);


--
-- TOC entry 3201 (class 2606 OID 16432)
-- Name: registroentrada fk_registroentrada_registraproducto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registroentrada
    ADD CONSTRAINT fk_registroentrada_registraproducto FOREIGN KEY (registraproducto) REFERENCES public.producto(codigoproducto);


--
-- TOC entry 3202 (class 2606 OID 16437)
-- Name: registrosalida fk_registrosalida_realizausuario; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registrosalida
    ADD CONSTRAINT fk_registrosalida_realizausuario FOREIGN KEY (realizausuario) REFERENCES public.usuario(codigousuario);


--
-- TOC entry 3203 (class 2606 OID 16442)
-- Name: registrosalida fk_registrosalida_registraproducto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.registrosalida
    ADD CONSTRAINT fk_registrosalida_registraproducto FOREIGN KEY (registraproducto) REFERENCES public.producto(codigoproducto);


-- Completed on 2023-02-17 19:00:44

--
-- PostgreSQL database dump complete
--

