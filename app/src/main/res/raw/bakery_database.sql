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
)#

CREATE TABLE element (
 _id INTEGER PRIMARY KEY AUTOINCREMENT,
 element_id TEXT,
 ingredient_id TEXT,
 name TEXT,
 amount INTEGER,
 hint TEXT,
 unit_name TEXT,
 symbol TEXT
)#


