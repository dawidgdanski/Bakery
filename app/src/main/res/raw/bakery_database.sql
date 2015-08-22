CREATE TABLE recipe (
 _id INTEGER PRIMARY KEY AUTOINCREMENT,
 recipe_id TEXT,
 title TEXT,
 description TEXT,
 image_url TEXT,
 UNIQUE(recipe_id)
)#

CREATE TABLE ingredient (
 _id INTEGER PRIMARY KEY AUTOINCREMENT,
 recipe_id TEXT,
 ingredient_id TEXT,
 name TEXT,
 UNIQUE(recipe_id, ingredient_id)
)#

CREATE TABLE element (
 _id INTEGER PRIMARY KEY AUTOINCREMENT,
 element_id TEXT,
 ingredient_id TEXT,
 name TEXT,
 amount INTEGER,
 hint TEXT,
 unit_name TEXT,
 symbol TEXT,
 UNIQUE(element_id, ingredient_id)
)#

CREATE VIRTUAL TABLE fts_recipe_with_ingredient (
 recipe_id,
 title,
 description,
 image_url,
 ingredient_name
)#

CREATE TRIGGER insert_fts_recipe_with_ingredient_after_recipe_insertion AFTER INSERT ON recipe
 BEGIN
 INSERT INTO
 fts_recipe_with_ingredient(recipe_id, title, description, image_url, ingredient_name)
 VALUES(NEW.recipe_id, NEW.title, NEW.description, NEW.image_url, NULL);
 END#

CREATE TRIGGER update_fts_recipe_with_ingredient_after_ingredient_insertion AFTER INSERT ON ingredient
 BEGIN
 UPDATE fts_recipe_with_ingredient
 SET ingredient_name = NEW.name
 WHERE recipe_id = NEW.recipe_id;
 END#