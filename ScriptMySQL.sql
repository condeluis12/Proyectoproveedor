create table proveedor(
Nombredelproveedor varchar(255),
Numerodeidentificacion  int NOT NULL AUTO_INCREMENT,
direccion varchar(255),
correoelectronico varchar(255),
cantidadvehiculos int,
 PRIMARY KEY (Numerodeidentificacion)
);

create table vehiculo(
Placadelvehiculo varchar(255),
Numeroidentificacionelconductor  int NOT NULL AUTO_INCREMENT,
Marca varchar(255),
Modelo varchar(255),
Nombredelconductor varchar(255),
Idproveedor int,
estado boolean,
 PRIMARY KEY (Numeroidentificacionelconductor),
 CONSTRAINT fk_proveedor FOREIGN KEY (Idproveedor) REFERENCES proveedor (Numerodeidentificacion)
);
