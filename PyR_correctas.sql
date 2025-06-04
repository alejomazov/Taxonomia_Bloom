use mydb;
Select *
from examenes e, preguntas p, respuestas r
where e.id_examen = p.id_examen AND
p.id_pregunta = r.id_pregunta AND
r.es_correcta = 1;

