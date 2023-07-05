
-- Crear la base de datos
CREATE DATABASE prueba;

-- Usar la base de datos
USE prueba;


CREATE TABLE public.users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    user_name VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE public.roles (
    id SERIAL PRIMARY KEY,
    rol_name character varying(255)
);
CREATE TABLE public.user_rol (
    user_id INT ,
    role_id SERIAL NOT NULL
);

CREATE TABLE public.vacancies (
    id SERIAL NOT NULL,
    company character varying(255),
    job_description character varying(2000),
    job_name character varying(255),
    location character varying(255),
    modality character varying(255)
);

INSERT INTO public.vacancies (company, job_description, job_name, location, modality)
VALUES
  ('Restaurante en Oaxaca', 'Se busca cocinero con experiencia en la preparación de platillos tradicionales oaxaqueños. El candidato ideal debe tener conocimientos en cocina regional oaxaqueña, técnicas de preparación y manejo de ingredientes locales. Será responsable de la preparación de platillos del menú, asegurando la calidad y presentación de los mismos. Debe tener habilidades culinarias destacadas, creatividad en la elaboración de recetas y capacidad para trabajar en un ambiente de alta demanda. Se requiere disponibilidad para trabajar en horarios rotativos y fines de semana. Ofrecemos un entorno de trabajo agradable, salario competitivo y la oportunidad de formar parte de un equipo culinario apasionado por la cocina oaxaqueña.', 'Cocinero', 'Oaxaca', 'Presencial'),
  ('Empresa de Construcción', 'Se busca albañil con experiencia en construcción de edificios y casas. El candidato ideal debe tener conocimientos en albañilería, manejo de herramientas y materiales de construcción. Será responsable de realizar trabajos de albañilería como levantamiento de paredes, colocación de ladrillos, mezcla de mortero, entre otros. Debe ser capaz de trabajar en equipo, seguir instrucciones y mantener un alto estándar de calidad en su trabajo. Se requiere disponibilidad para trabajar en diferentes ubicaciones de la ciudad. Ofrecemos salario competitivo y oportunidades de crecimiento en una empresa líder en el sector de la construcción.', 'Albañil', 'Ciudad', 'Presencial'),
   ('TV Azteca', 'TV Azteca está buscando un talentoso y apasionado reportero para unirse a nuestro equipo de noticias. El candidato ideal debe tener experiencia en periodismo, excelente capacidad de investigación, habilidades de comunicación efectivas y una pasión por informar noticias veraces y objetivas. Será responsable de cubrir eventos, realizar entrevistas, redactar noticias y presentar información de manera clara y concisa frente a la cámara. Debe tener disponibilidad para trabajar en horarios flexibles y adaptarse a situaciones de noticias de última hora. Se requiere conocimiento de los estándares periodísticos y éticos, así como un buen dominio del idioma español. Ofrecemos la oportunidad de trabajar en un entorno dinámico, colaborativo y emocionante, así como un salario competitivo y posibilidades de crecimiento profesional.', 'Reportero', 'Ciudad de México', 'Presencial');