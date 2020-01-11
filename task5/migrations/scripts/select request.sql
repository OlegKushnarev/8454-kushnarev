SELECT * FROM task5.manufecturer;

--GET/manufacturers
SELECT * FROM task5.manufacturer AS m WHERE LOWER(m.title) LIKE LOWER('%APC%');

--GET/manufacturers/{id}
SELECT * FROM task5.manufacturer WHERE id = 4;

--GET/manufacturers/{id}/products
SELECT * FROM task5.product WHERE manufacturerId = 3;

--GET/manufacturers/{id}/categories
SELECT DISTINCT task5.category.id, task5.category.name
	FROM task5.category
		INNER JOIN task5.product ON task5.category.id = task5.product.categoryId
		INNER JOIN task5.manufacturer ON task5.manufacturer.id = task5.product.manufacturerId
	WHERE task5.manufacturer.id = 3;


SELECT * FROM task5.category;

--GET/categories
SELECT * FROM task5.category AS c WHERE LOWER(c.title) LIKE LOWER('%Клемма%');

--GET/categories/{id}
SELECT * FROM task5.category WHERE id = 5;

--GET/categories/{id}/manufacturers
SELECT DISTINCT task5.manufacturer.id, task5.manufacturer.title
	FROM task5.manufacturer
		INNER JOIN task5.product ON task5.manufacturer.id = task5.product.manufacturerId
		INNER JOIN task5.category ON task5.category.id = task5.product.categoryId
	WHERE task5.category.id = 3;

--GET/categories/{id}/products
SELECT * FROM task5.product WHERE categoryId = 3;


SELECT * FROM task5.product;

--GET/products
SELECT *
	FROM task5.product
		INNER JOIN task5.manufacturer ON task5.manufacturer.id = task5.product.manufacturerId
		INNER JOIN task5.category ON task5.category.id = task5.product.categoryId
	WHERE task5.product.title = 'STS 2,5-TWIN'
	AND task5.manufacturer.title = 'Phoenix Contact';

--GET/products/{id}
SELECT * FROM task5.product WHERE id = 1;

