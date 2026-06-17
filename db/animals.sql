-- public.animals
SELECT * FROM public.animals;

INSERT INTO public.animals (name, area)
VALUES ('Гризли', 'США');

UPDATE public.animals
SET name = 'Гризли'
WHERE name IS NOT NULL;