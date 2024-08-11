INSERT INTO paises(id, nombre) VALUES 
(1, 'Mexico'),
(2, 'España'),
(3, 'Estados Unidos'),
(4, 'Francia'),
(5, 'Italia');

INSERT INTO regiones(id, pais_id, nombre) VALUES 
(1, 1, 'Nuevo Leon'),
(2, 2, 'Cataluña'),
(3, 3, 'Nueva york'),
(4, 4, 'Provenza-Alpes-Costa Azul'),
(5, 5, 'Lombardia');

INSERT INTO ciudades(id, region_id, nombre) VALUES 
(1, 1, 'Monterrey'),
(2, 2, 'Girona'),
(3, 3, 'Buffalo'),
(4, 4, 'Marsella'),
(5, 5, 'Como');

INSERT INTO ubicaciones(id, codigo_postal, ciudad_id, direccion1, direccion2) VALUES
(1, '64000', 1, 'Av. Constitución 1000', 'Apto 201'),      
(2, '17001', 2, 'Carrer de la Rambla 50', 'Piso 3'),        
(3, '14201', 3, '123 Elm St', 'Suite 200'),                 
(4, '13001', 4, '6 Rue de la République', 'Bâtiment B'),    
(5, '22100', 5, 'Via Roma 45', 'Interno 10'),
(6, '70001', 1, 'Av. Juárez 123', 'Oficina 301'),    
(7, '08002', 2, 'Carrer del Mar 22', 'Piso 4'),         
(8, '14202', 3, '456 Oak St', 'Suite 505');   

INSERT INTO contactos(id, extension, fax, telefono, email) VALUES
(1, '101', '555-1234', '555-5678', 'contacto1@monterrey.com'),   
(2, '202', '555-2345', '555-6789', 'contacto2@girona.com'),      
(3, '303', '555-3456', '555-7890', 'contacto3@buffalo.com'),     
(4, '404', '555-4567', '555-8901', 'contacto4@marsella.com'),    
(5, '505', '555-5678', '555-9012', 'contacto5@como.com'),
(6, '606', '555-6781', '555-1234', 'contacto6@monterrey.com'),  -- Contacto en Monterrey
(7, '707', '555-7892', '555-2345', 'contacto7@girona.com'),     -- Contacto en Girona
(8, '808', '555-8903', '555-3456', 'contacto8@buffalo.com');

INSERT INTO cliente_direccion(id, contacto_id, ubicacion_id) VALUES
(1, 1, 1), 
(2, 2, 2),  
(3, 3, 3),  
(4, 4, 4), 
(5, 5, 5);

INSERT INTO estados(id, nombre) VALUES
(1, 'Activo'),       
(2, 'Pendiente'),    
(3, 'Rechazado'),    
(4, 'Aprobado'),    
(5, 'En Proceso'); 

INSERT INTO creditos(id, limite, estado_id) VALUES
(1, 5000, 1), 
(2, 10000, 2),
(3, 1500, 3),  
(4, 7500, 4), 
(5, 3000, 5);  

INSERT INTO oficinas(id, contacto_id, ubicacion_id) VALUES
(1, 6, 6), 
(2, 7, 7),  
(3, 8, 8); 

INSERT INTO cuentas(id, username, password, role) VALUES
(1, 'Juan Perez', 'contraseña123', 'ADMIN'),
(2, 'Ana Gonzalez', '1234abcd', 'USER'),   
(3, 'Luis Matinez', 'securePass1!', 'USER');

INSERT INTO empleados(id, contacto_id, cuenta_id, jefe_id, oficina_id, apellidos, email, nombres, puesto) VALUES
(1, 6, 1, 3, 1, 'Pérez', 'juan.perez@monterrey.com', 'Juan', 'Gerente General'),
(2, 7, 2, 1, 2, 'González', 'ana.gonzalez@girona.com', 'Ana', 'Coordinadora'),  
(3, 8, 3, 2, 3, 'Martínez', 'luis.martinez@buffalo.com', 'Luis', 'Analista');   

INSERT INTO clientes(credito_id, cuenta_id, empleado_id, ubicacion_id, apellido, nombre) VALUES
(1, 1, 1, 6, 'García', 'Roberto'),      
(2, 2, 2, 7, 'Martínez', 'Laura'),     
(3, 3, 3, 8, 'Sánchez', 'Miguel');   

INSERT INTO pedidos(id, cliente_id, estado_id, fecha_entregada, fecha_esperada, fecha_pedido, comentarios, metodo_pago) VALUES
(1, 1, 1, '2024-08-05', '2024-08-01', '2024-07-28', 'Pedido urgente, por favor verificar el stock.', 'Tarjeta de Crédito'), 
(3, 3, 4, '2024-08-09', '2024-08-05', '2024-08-01', 'Incluir nota de agradecimiento con el pedido.', 'PayPal'),       
(4, 1, 5, NULL, '2024-08-15', '2024-08-02', 'Revisar stock y preparar para envío.', 'Efectivo'),                        
(5, 2, 3, '2024-08-12', '2024-08-08', '2024-08-01', 'Pedido entregado fuera de plazo.', 'Tarjeta de Crédito');

INSERT INTO proveedores(id, ubicacion_id, apellidos, nombre_empresa, nombres) VALUES
(1, 6, 'Ramírez', 'ElectroTech Inc.', 'Carlos'),      -- Proveedor con ubicación en Monterrey
(2, 7, 'García', 'Muebles Modernos S.A.', 'Ana'),      -- Proveedor con ubicación en Girona
(3, 8, 'Martínez', 'Papelería Global', 'Luis');        -- Proveedor con ubicación en Buffalo

INSERT INTO gama_producto(id, descripcion, imagen, nombre) VALUES
(1, 'Electrodomésticos de última generación', 'electrodomesticos','Electrodomésticos'),
(2, 'Muebles modernos para oficina y hogar', 'muebles', 'Muebles Modernos'),
(3, 'Papelería y suministros de oficina', 'papeleria', 'Papelería de Oficina'),
(4, 'Componentes industriales y maquinaria', 'componentes', 'Componentes Industriales'),
(5, 'Alimentos gourmet y bebidas especiales', 'alimentos', 'Alimentos y Bebidas');

INSERT INTO dimensiones(id, alto, ancho, largo, peso) VALUES
(1, 30.0, 20.0, 10.0, 1.5),
(2, 60.0, 40.0, 20.0, 5.0), 
(3, 90.0, 50.0, 40.0, 10.0),
(4, 15.0, 15.0, 15.0, 0.8),  
(5, 120.0, 80.0, 60.0, 20.0);

INSERT INTO stock(id, stock_actual, stock_maximo, stock_minimo) VALUES
(1, 150, 200, 50),  
(2, 80, 100, 20),   
(3, 250, 300, 100), 
(4, 30, 50, 10),   
(5, 120, 150, 30); 

INSERT INTO productos(id, precio, dimension_id, gama_producto_id, proveedor_id, stock_id, descripcion, nombre) VALUES
(1, 499.99, 1, 1, 1, 1, 'Refrigerador de alta eficiencia con múltiples compartimentos.', 'Refrigerador EcoCool'),
(2, 299.99, 2, 2, 2, 2, 'Silla ergonómica con soporte lumbar ajustable.', 'Silla ErgoFlex'),
(3, 59.99, 3, 3, 3, 3, 'Paquete de 100 hojas de papel de oficina A4.', 'Papel OfficePack'),
(4, 799.99, 4, 4, 4, 4, 'Taladro de alta potencia para uso industrial.', 'Taladro PowerDrill'),
(5, 19.99, 5, 5, 5, 5, 'Caja de chocolates gourmet con selección variada.', 'Chocolates Delicias');

INSERT INTO detalles_pedidos(id, cantidad, numero_linea, precio_unidad, pedido_id, producto_id) VALUES
(1, 2, 1, 499.99, 1, 1),
(3, 5, 1, 59.99, 5, 3),
(4, 1, 1, 799.99, 3, 4), 
(5, 3, 1, 19.99, 4, 5); 

INSERT INTO pagos(id, monto, fecha_pago, pedido_id) VALUES
(1, 799.98, '2024-08-05', 1),
(3, 799.99, '2024-08-07', 3),  
(4, 59.97, '2024-08-08', 4), 
(5, 59.97, '2024-08-09', 5);