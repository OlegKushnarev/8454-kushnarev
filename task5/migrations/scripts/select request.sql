SELECT * FROM task5.manufecturer;

--GET/manufecturers
SELECT * FROM task5.manufecturer WHERE title = 'APC';

--GET/manufecturers/{id}
SELECT * FROM task5.manufecturer WHERE id = 4;

--GET/manufecturers/{id}/products
SELECT * FROM task5.product WHERE manufecturerId = 3;

--GET/manufecturers/{id}/categories
SELECT DISTINCT task5.category.id, task5.category.name
	FROM task5.category
		INNER JOIN task5.product ON task5.category.id = task5.product.categoryId
		INNER JOIN task5.manufecturer ON task5.manufecturer.id = task5.product.manufecturerId
	WHERE task5.manufecturer.id = 3;


SELECT * FROM task5.category;

--GET/categories
SELECT * FROM task5.category WHERE name = 'Клемма';

--GET/categories/{id}
SELECT * FROM task5.category WHERE id = 5;

--GET/categories/{id}/manufecturers
SELECT DISTINCT task5.manufecturer.id, task5.manufecturer.title
	FROM task5.manufecturer
		INNER JOIN task5.product ON task5.manufecturer.id = task5.product.manufecturerId
		INNER JOIN task5.category ON task5.category.id = task5.product.categoryId
	WHERE task5.category.id = 3;

--GET/categories/{id}/products
SELECT * FROM task5.product WHERE categoryId = 3;


SELECT * FROM task5.product;

--GET/products 5 запросов должно быть
SELECT * FROM task5.product WHERE task5.product.title = 'STS 2,5-TWIN';

SELECT task5.product.id, task5.product.title, task5.product.productId, task5.product.description
	FROM task5.product
		INNER JOIN task5.manufecture ON task5.manufecture.id = task5.product.manufectureId
	WHERE task5.manufecture.title = 'Phoenix Contact';

SELECT task5.product.id, task5.product.title, task5.product.productId, task5.product.description
	FROM task5.product
		INNER JOIN task5.category ON task5.category.id = task5.product.categoryId
	WHERE task5.category.name = 'Клемма';

SELECT * FROM task5.product WHERE task5.product.description = 'Реле 24 В';

SELECT task5.product.id, task5.product.title, task5.product.productId, task5.product.description
	FROM task5.product
		INNER JOIN task5.manufecture ON task5.manufecture.id = task5.product.manufectureId
		INNER JOIN task5.category ON task5.category.id = task5.product.categoryId
	WHERE task5.product.title = 'STS 2,5-TWIN'
	AND task5.manufecture.title = 'Phoenix Contact'
	AND task5.category.name = 'Клемма'
	AND task5.product.description = 'Клемма 2,5 кв.мм, серая';


SELECT *
	FROM task5.product
		INNER JOIN task5.manufecture ON task5.manufecture.id = task5.product.manufectureId
		INNER JOIN task5.category ON task5.category.id = task5.product.categoryId
	WHERE task5.product.title = 'STS 2,5-TWIN'
	AND task5.manufecture.title = 'Phoenix Contact';

--GET/products/{id}
SELECT * FROM task5.product WHERE id = 1;



DROP DATABASE IF EXISTS task5;

