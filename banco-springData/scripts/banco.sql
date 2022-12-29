DROP DATABASE banco_heredia;
CREATE DATABASE IF NOT EXISTS banco_heredia;
USE banco_heredia;


SELECT * FROM usuarios;
SELECT * FROM cuentas;
SELECT * FROM cuentas c LEFT JOIN usuarios u ON c.id_cuenta = u.id_cuenta WHERE u.nombre = "juan";
SELECT * FROM cuentas c LEFT JOIN usuarios u ON c.id_cuenta = u.id_cuenta WHERE u.nombre = "lautaro";