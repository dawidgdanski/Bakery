CREATE TABLE recipe (
 _id INTEGER PRIMARY KEY AUTOINCREMENT,
 recipe_id TEXT,
 title TEXT,
 description TEXT,
 image_url TEXT
)#

CREATE TABLE ingredient (
 _id INTEGER PRIMARY KEY AUTOINCREMENT,
 recipe_id TEXT,
 ingredient_id TEXT,
 name TEXT
)#

CREATE TABLE element (
 _id INTEGER PRIMARY KEY AUTOINCREMENT,
 element_id TEXT,
 ingredient_id TEXT,
 recipe_id TEXT,
 name TEXT,
 amount INTEGER,
 hint TEXT,
 unit_name TEXT,
 symbol TEXT
)#

CREATE VIRTUAL TABLE fts_recipe_with_ingredient USING fts3 (
 recipe_id,
 title,
 description,
 image_url,
 ingredient_name,
 element_names
)#

CREATE TRIGGER insert_fts_recipe_with_ingredient_after_recipe_insertion BEFORE INSERT ON recipe
 BEGIN
 INSERT INTO
 fts_recipe_with_ingredient(recipe_id, title, description, image_url, ingredient_name)
 VALUES(NEW.recipe_id, NEW.title, NEW.description, NEW.image_url, NULL);
 END#

CREATE TRIGGER update_fts_recipe_with_ingredient_after_ingredient_insertion BEFORE INSERT ON ingredient
 BEGIN
 UPDATE fts_recipe_with_ingredient
 SET ingredient_name =
  CASE
  WHEN ingredient_name IS NULL THEN NEW.name
  ELSE ingredient_name || ', ' || NEW.name
  END
  WHERE recipe_id = NEW.recipe_id;
 END#

 CREATE TRIGGER update_fts_recipe_with_ingredient_after_element_insertion BEFORE INSERT ON element
  BEGIN
  UPDATE fts_recipe_with_ingredient
  SET element_names =
   CASE
   WHEN element_names IS NULL THEN NEW.name
   ELSE element_names || ', ' || NEW.name
   END
   WHERE recipe_id = NEW.recipe_id;
  END#