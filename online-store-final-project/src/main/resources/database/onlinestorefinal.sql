create schema onlinestore;
use onlinestore;

create table accounts (
ACCOUNT_ID int not null auto_increment,
USER_EMAIL varchar(100) not null,
USER_PASSWORD varchar(200) not null,
USER_NAME varchar(50) NOT NULL,
PHONE varchar(50),
ADDRESS varchar(255) NOT NULL,
ACCOUNT_ROLE varchar(50) not null,
primary key (ACCOUNT_ID)
);

create table category (
CATEGORY_ID int NOT NULL AUTO_INCREMENT,
CATEGORY_NAME varchar(50),
PRIMARY KEY (CATEGORY_ID)
);

create table subcategory (
SUBCATEGORY_ID int not null auto_increment,
SUBCATEGORY_NAME varchar(50),
CATEGORY_ID int not null,
CONSTRAINT CATEGORY_ID_FK FOREIGN KEY (CATEGORY_ID) REFERENCES category (CATEGORY_ID),
PRIMARY KEY (SUBCATEGORY_ID)
);

create table product (
PRODUCT_ID varchar(50) not null,
PRODUCT_IMAGE varchar(200),
PRODUCT_NAME varchar(100) not null,
PRODUCT_DESCRIPTION varchar(500),
PRODUCT_PRICE decimal(6,2) not null,
PRODUCT_STOCK int not null,
PRODUCT_STATUS int not null,
CATEGORY_ID int not null,
CONSTRAINT PRODUCT_CATEGORY_ID_FK FOREIGN KEY (CATEGORY_ID) REFERENCES category (CATEGORY_ID),
SUBCATEGORY_ID int not null,
CONSTRAINT PRODUCT_SUBCATEGORY_ID_FK FOREIGN KEY (SUBCATEGORY_ID) REFERENCES subcategory (SUBCATEGORY_ID),
PRIMARY KEY (PRODUCT_ID)
);

create table orders (
ORDER_ID int(8) not null auto_increment,
AMOUNT decimal(6,2) not null,
CUSTOMER_ADDRESS VARCHAR(255) not null,
CUSTOMER_EMAIL VARCHAR(128) not null,
CUSTOMER_NAME VARCHAR(255) not null,
CUSTOMER_PHONE VARCHAR(128) not null,
ORDER_DATE date not null,
ORDER_STATUS integer not null,
PRIMARY KEY (ORDER_ID) 
);

create table order_line (
ORDER_LINE_ID int(8) not null auto_increment,
ORDER_ID int(8) not null,
PRODUCT_ID varchar(50) not null,
CATEGORY_ID int not null,
SUBCATEGORY_ID int not null,
PRODUCT_IMAGE varchar(200),
PRODUCT_NAME varchar(100) not null,
PRODUCT_DESCRIPTION varchar(200),
PRODUCT_PRICE decimal(6,2) not null,
QUANTITY integer not null,
CONSTRAINT PRODUCT_ID_FK FOREIGN KEY (PRODUCT_ID) REFERENCES product (PRODUCT_ID),
CONSTRAINT ORDER_ID_FK FOREIGN KEY (ORDER_ID) REFERENCES orders (ORDER_ID),
PRIMARY KEY (ORDER_LINE_ID)
);


INSERT INTO category(CATEGORY_NAME) VALUES ("Meat and Seafood"), ("Produce"), ("Pantry"), ("Liquor and Beverages"),
("Bakery"), ("Dairy"), ("Deli and Prepared Foods");

INSERT INTO subcategory(SUBCATEGORY_NAME,CATEGORY_ID) VALUES ("Beef", 1), ("Pork", 1), ("Chicken", 1),
("Seafood", 1), ("Fruit", 2), ("Vegetable", 2), ("Rice, Grains and Pasta",3), ("Cooking and Baking",3),("Spices and Seasonings",3),
("Wine", 4), ("Beer", 4), ("Coffee", 4), ("Tea",4), ("Soda",4),("Juice",4), ("Breads and Pastries", 5), ("Cakes",5), ("Cookies and Muffins",5),
("Butter and Margarine", 6), ("Cheese", 6), ("Milk and Cream", 6), ("Meal kits",7), ("Deli Meats",7);

-- INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS) 
-- VALUES ("MS0001","Beef Caldereta",280.00,100,"For a classic hearty Filipino stew. Specification: Diced Cut 500g",1,1,"https://cdn.shopify.com/s/files/1/2487/0212/products/500g-beef-caldereta-500g-29894240075956_600x600.png?v=1628180280",0);

-- INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS) 
-- VALUES ("PR0001","Red Apple",53.00,500,"Fuji apple sold per piece",2,5,"https://cdn.shopify.com/s/files/1/2487/0212/products/red-apple-113-180-190-grms-red-apple-180-190-grms-23269492293812_600x600.jpg?v=1627899458",0),
-- ("PR0002","Fuji Apple",64.00,400,"Red apple / 180-190 grms",2,5,"https://cdn.shopify.com/s/files/1/2487/0212/products/per-pc-fuji-apple-per-pc-29873024630964_587x599.jpg?v=1627668272",0),
-- ("PA0016","Magnolia All Purpose Flour 400G",40.25,300,"All Purpose Flour good for baking, cooking and creating sauces. No need to go through traffic just to get your favorite premium food and beverage products!",3,8,"https://cdn.shopify.com/s/files/1/2487/0212/products/magnolia-all-purpose-flour-400g-30537455960244_946x1352.jpg?v=1632293596",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("MS0001","Beef Caldereta",280.00,100,"For a classic hearty Filipino stew. Specification: Diced Cut 500g",1,1,"https://cdn.shopify.com/s/files/1/2487/0212/products/500g-beef-caldereta-500g-29894240075956_600x600.png?v=1628180280",0),
("MS0002","Beef Stew Cut",280.00,100,"Perfect for your pares, mechado, and pochero.Specification: Diced cut 500 g",1,1,"https://cdn.shopify.com/s/files/1/2487/0212/products/500g-beef-stew-cut-500g-29846120005812_600x600.png?v=1628129692",0),
("MS0003","Angus Ground Beef",340.00,100,"Versatile, flavorful and economical. Shape into burger patties, meatballs or meatloaf; or brown and crumble for a variety of dishes. 1 kg",1,1,"https://cdn.shopify.com/s/files/1/2487/0212/products/1-kg-angus-ground-beef-1kg-29906153177268_600x600.png?v=1628123932",0),
("MS0004","Beef Bulalo",370.00,100,"New Zealand beef shanks. Bringing your Tagaytay favorite to your kitchen. Specification: -cut 3/4 inch 1 kg",1,1,"https://cdn.shopify.com/s/files/1/2487/0212/products/1kg-beef-bulalo-1kg-29905794990260_600x600.png?v=1628128434",0),
("MS0005","Beef Samgyupsal / Belly",448.00,100,"A.K.A Woosamgyeop. Premium sliced beef belly ready for that korean barbeque night! Specifications: Sliced 2.5mm",1,1,"https://cdn.shopify.com/s/files/1/2487/0212/products/1kg-beef-samgyupsal-belly-28279230365876_600x600.jpg?v=1628197391",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("MS0006","Ground Pork",160.00,100,"Ground Pork 500g",1,2,"https://cdn.shopify.com/s/files/1/2487/0212/products/ground-pork-500g-28178931351732_600x600.png?v=1628124851",0),
("MS0007","Pork Liempo",192.00,100,"Pork Belly Bone in Skin on Specifications: cut 3/4 inch .5kg",1,2,"https://cdn.shopify.com/s/files/1/2487/0212/products/500g-pork-liempo-5kg-28519071449268_600x600.jpg?v=1628195389",0),
("MS0008","Pork Loin Sliced 500 g",286.00,100,"Frozen locally grown boneless pork loin slices.",1,2,"https://cdn.shopify.com/s/files/1/0244/3401/6290/products/pork-loin-sliced-619310_900x.jpg?v=1626331712",0),
("MS0009","Pork Lacone (Pata) Chops 500 g",289.00,100,"British pork knuckles cut in 1/2 inch chops.  Locally known as pata.",1,2,"https://cdn.shopify.com/s/files/1/0244/3401/6290/products/pork-lacone-pata-chops-459141_900x.jpg?v=1626937385",0),
("MS0010","Farm's Choice Pork Sinigang Cut approx. 1.6kg",313.20,100,"Farm's Choice Pork Sukiyaki approx. 940g",1,2,"https://d5anwn521ljmo.cloudfront.net/catalog/product/cache/7ea2575b56506914ef5fff8fcbc8a182/8/0/800047-1_1_1.jpg",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("MS0011","Ground Chicken Breast",95.00,100,"Ground Chicken Breast (500g)",1,3,"https://cdn.shopify.com/s/files/1/2487/0212/products/ground-chicken-breast-500g-29841791877300_800x800.jpg?v=1627945175",0),
("MS0012","Bounty Fresh Whole Everyday Chicken 850g",153.00,100,"Bounty Fresh Whole Everyday Chicken 850g",1,3,"https://cdn.shopify.com/s/files/1/0358/1116/4298/products/68.jpg?v=1627373622",0),
("MS0013","Chicken Fingers",185.00,100,"Chicken Fingers Chicken Fingers (500grams)",1,3,"https://cdn.shopify.com/s/files/1/2487/0212/products/chicken-fingers-500grams-29901581975732_511x511.jpg?v=1628133644",0),
("MS0014","Chicken Wings",400.00,100,"Chicken Wings 1kg Pack",1,3,"https://cdn.shopify.com/s/files/1/2487/0212/products/chicken-wings-1kg-pack-29934635024564_946x1419.jpg?v=1628129520",0),
("MS0015","Chicken Drumsticks",200.00,100,"Chicken Drumsticks (1kg)",1,3,"https://cdn.shopify.com/s/files/1/2487/0212/products/chicken-drumsticks-1kg-29846778609844_800x800.jpg?v=1628135806",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("MS0016","Pompano Whole",159.60,100,"Pompano fish is low in sodium and is a very good source of Protein and Vitamin B12. To bring out its flavor, you can steam or fry it.",1,4,"https://cdn.shopify.com/s/files/1/2487/0212/products/pompano-whole-500-600g-29854905630900_600x600.png?v=1628138878",0),
("MS0017","Tempura Fish Fillet",300.00,100,"Tempura Fish Fillet 325g",1,4,"https://cdn.shopify.com/s/files/1/2487/0212/products/tempura-fish-fillet-325g-28600813519028_600x600.jpg?v=1627987340",0),
("MS0018","Salmon Head and Tail",360.00,100,"Salmon Head and Tail (1kg)",1,4,"https://cdn.shopify.com/s/files/1/2487/0212/products/salmon-head-and-tail-1kg-28956963373236_600x600.png?v=1628032514",0),
("MS0019","Salmon Belly (GG) approx. 1kg",373.75,100,"Salmon Belly (GG) approx. 1kg",1,4,"https://d5anwn521ljmo.cloudfront.net/catalog/product/cache/7ea2575b56506914ef5fff8fcbc8a182/8/0/800591-1_1.jpg",0),
("MS0020","Shrimp Popcorn",390.00,100,"Shrimp Popcorn 300g",1,4,"https://cdn.shopify.com/s/files/1/2487/0212/products/shrimp-popcorn-300g-29837033177268_600x600.jpg?v=1628094585",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("PR0001","Red Apple",53.60,100,"Red apple / 180-190 grms",2,5,"https://cdn.shopify.com/s/files/1/2487/0212/products/red-apple-113-180-190-grms-red-apple-180-190-grms-23269492293812_600x600.jpg?v=1627899458",0),
("PR0002","Banana Lakatan/Lacatan",100.00,100,"Lakatan/Lacatan 1 piling",2,5,"https://cdn.shopify.com/s/files/1/2487/0212/products/banana-lakatan-lacatan-28247941939380_600x573.jpg?v=1628039516",0),
("PR0003","Papaya Green",105.00,100,"Papaya Green 700-700g",2,5,"https://cdn.shopify.com/s/files/1/2487/0212/products/papaya-green-700-700g-papaya-green-700-700g-23267909632180_600x600.jpg?v=1628003337",0),
("PR0004","Pineapple",114.00,100,"Medium size (1 kilo/pc)",2,5,"https://cdn.shopify.com/s/files/1/2487/0212/products/pineapple-md2size-12-1-kilo-pc-pineapple-md2size-12-1-kilo-pc-23271582236852_600x600.jpg?v=1627214088",0),
("PR0005","Pomelo",171.00,100,"Pomelo (700-800grams)",2,5,"https://cdn.shopify.com/s/files/1/2487/0212/products/pomelo-700-800grams-pomelo-700-800grams-29909904490676_600x600.jpg?v=1628106835",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("PR0006","Alugbati",76.00,100,"Alugbati 500g",2,6,"https://cdn.shopify.com/s/files/1/2487/0212/products/alugbati-500g-alugbati-500g-29804102451380_600x600.jpg?v=1628025827",0),
("PR0007","Pechay Native",100.00,100,"Pechay Native 500g",2,6,"https://cdn.shopify.com/s/files/1/2487/0212/products/pechay-native-500g-pechay-native-500g-23268489035956_600x600.jpg?v=1628142289",0),
("PR0008","Sayote",100.00,100,"Sayote 500g",2,6,"https://cdn.shopify.com/s/files/1/2487/0212/products/sayote-27929038356660_600x600.jpg?v=1627148745",0),
("PR0009","Okra",221.00,100,"Okra 1kg",2,6,"https://cdn.shopify.com/s/files/1/2487/0212/products/okra-28362782769332_462x600.jpg?v=1617529318",0),
("PR0010","Eggplant",241.00,100,"Eggplant 1kg",2,6,"https://cdn.shopify.com/s/files/1/2487/0212/products/eggplant-28103228719284_600x375.jpg?v=1627097874",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("PT0001","Biryani Long Grain Rice 500g",71.00,100,"Cooking rice is simple to do but there is a trick to getting it to come out just right. Cooking the best rice ,can give you the best recipes,Get this Biryani Long Grain Rice today ,and you'll end up with rice that's wonderfully tender, never mushy.",3,7,"https://cdn.shopify.com/s/files/1/2487/0212/products/biryani-long-grain-rice-500g-29494623142068_600x599.jpg?v=1625200934",0),
("PT0002","Spanish Gate - Long Grain Rice 500g",71.00,100,"Cooking rice is simple to do but there is a trick to getting it to come out just right. Cooking the best rice ,can give you the best recipes,Get this Spanish Gate - Long Grain Rice today ,and you'll end up with rice that's wonderfully tender, never mushy.",3,7,"https://cdn.shopify.com/s/files/1/2487/0212/products/spanish-gate-long-grain-rice-500g-28994798321844_600x467.jpg?v=1622280004",0),
("PT0003","Biryani Fragrance Rice 500g",71.00,100,"Cooking rice is simple to do but there is a trick to getting it to come out just right. Cooking the best rice, can give you the best recipes, Get this Biryani Fragrance Rice today, and you'll end up with rice that's wonderfully tender, never mushy.",3,7,"https://cdn.shopify.com/s/files/1/2487/0212/products/biryani-fragrance-rice-500g-30049536278708_600x467.jpg?v=1629099504",0),
("PT0004","Black rice Malagkit",130.00,100,"Black rice Malagkit (1kg)",3,7,"https://cdn.shopify.com/s/files/1/2487/0212/products/black-rice-malagkit-1kg-29910594388148_600x600.png?v=1628094048",0),
("PT0005","Gate of India - Basmati Rice 500g",143.00,100,"Cooking rice is simple to do but there is a trick to getting it to come out just right. Cooking the best rice ,can give you the best recipes,Get this Gate of India - Basmati Rice today ,and you'll end up with rice that's wonderfully tender, never mushy.",3,7,"https://cdn.shopify.com/s/files/1/2487/0212/products/gate-of-india-basmati-rice-500g-28300038570164_600x582.jpg?v=1617008663",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("PT0006","Magnolia All Purpose Flour 400G",40.25,100,"All Purpose Flour good for baking, cooking and creating sauces. No need to go through traffic just to get your favorite premium food and beverage products!",3,8,"https://cdn.shopify.com/s/files/1/2487/0212/products/magnolia-all-purpose-flour-400g-30537455960244_946x1352.jpg?v=1632293596",0),
("PT0007","Wheat Flour Chakki Atta 500g",50.00,100,"Whole Wheat Flour (Atta) or Chakki Fresh Atta, manufactured from best quality Malwa Wheat (Madhya Pradesh), is available both in Consumer packs of 1kg, 2kg, 5 Kg,",3,8,"https://cdn.shopify.com/s/files/1/2487/0212/products/wheat-flour-chakki-atta-500g-28995063709876_600x553.jpg?v=1622270284",0),
("PT0008","Atta Whole Wheat Flour 500g",55.00,100,"Atta Whole Wheat Flour 500g Flour is, well, an all-around good flour to use for baking breads, cakes, muffins, and for mixing up a batch of pancake batter.  13% minimum protein. We also have different kinds of flour for the special pastry.",3,8,"https://cdn.shopify.com/s/files/1/2487/0212/products/atta-whole-wheat-flour-500g-28995064463540_600x529.jpg?v=1622276922",0),
("PT0009","Coconut King Organic Extra Virgin Coconut Oil VCO",205.20,100,"100% pure and all natural. It can be taken as a dietary supplement to boost immune systems, fight heart disease, and manage diabetes and aid weight loss. It can also be applied externally to skin and hair.",3,8,"https://cdn.shopify.com/s/files/1/2487/0212/products/coconut-king-extra-virgin-coconut-oil-22985152856244_568x568.jpg?v=1614328572",0),
("PT0010","Villa Vittoria Red Balsamic Vinegar 500ml",231.00,100,"Very dark, concentrated, and intensely flavoured vinegar originating in Italy, made wholly or partially from grape must.",3,8,"https://cdn.shopify.com/s/files/1/2487/0212/products/villa-vittoria-red-balsamic-vinegar-500ml-29850729742516_600x600.jpg?v=1627534646",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("PT0011","Ajinomoto Umami Seasoning Shaker 100g",34.75,100,"Ajinomoto Umami Seasoning is the world’s number one umami seasoning which provides the taste of umami to food. Made from the finest quality crops like sugarcane and tapioca in a natural fermentation process, Ajinomoto Umami Seasoning delivers the purest MSG for the most delicious dishes.",3,9,"https://cf.shopee.ph/file/a31a7d497b90329fbd310ba0a1684a3a",0),
("PT0012","Maggi Magic Sarap All-in-One Seasoning Granules 120g",44.75,100,"Maggi Magic Sarap is an All-in-One seasoning made with all the ingredients you need to transform food from good to great. It is made with fresh garlic & onion, real meat, and spices. Because it is All-in-One, it is perfect to use in any kind of dish – whether it be stir-fried, fried, soups, or stews.",3,9,"https://ph-live-05.slatic.net/p/509ca48b57d000e57defbdd2a3121a11.jpg_720x720q80.jpg_.webp",0),
("PT0013","Mama Sita's Tamarind Seasoning Paste 227g",48.75,100,"Made from fresh sour tamarind thickened with yam for a truly fruity sinigang. It has a more natural flavor because it is in paste form.",3,9,"https://mamasitas.com/arabia/wp-content/uploads/sites/2/2015/08/Biglang-Sinigang-Hot.png",0),
("PT0014","Badia Cayenne Pepper 49.6g",64.75,100,"This intense ground hot chili will add a touch of spice to your favorite dishes, commonly used in Mexican food as well as Szechuan, Indian and Cajun dishes.",3,9,"https://cf.shopee.ph/file/c4a3862e89cd1ae2ac2327f65bddff92",0),
("PT0015","Badia Garlic Salt 453.6g",89.75,100,"Garlic is one of the most useful and appetizing condiments due to its distinctive and characteristic flavor. It is the ideal accompaniment to pasta, rice, poultry, beef, seafood and all vegetables",3,9,"https://cf.shopee.ph/file/b8c81ae075c1cd7e7774875dc6c4e2af",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("LB0001","Vina Lanzar Red Semi Sweet 750ML",321.00,100,"This is a medium sweet wine that does not dry and has a sweet taste that goes well with everyday and casual occasions, especially for those new to wine. Popular Spanish wine at reasonable prices and affordable taste.",4,10,"https://cdn.shopify.com/s/files/1/2487/0212/products/vina-lanzar-red-semi-sweet-750ml-18658348564631_600x600.png?v=1612251405",0),
("LB0002","Lindeman Cawarra Merlot",350.00,100,"Lindeman Cawarra Merlot 750ML",4,10,"https://cdn.shopify.com/s/files/1/2487/0212/products/lindeman-cawarra-merlot-750ml-18661539676311_600x600.jpg?v=1612438307",0),
("LB0003","Nos Amours Blanc de blanc",428.00,100,"Nos Amours Blanc de blanc 750ml",4,10,"https://cdn.shopify.com/s/files/1/2487/0212/products/nos-amours-blanc-de-blanc-750ml-nos-amours-blanc-de-blanc-750ml-23277781221556_600x600.png?v=1628150072",0),
("LB0004","Camelot Cabernet Sauvignon",457.00,100,"Camelot Cabernet Sauvignon 750ml",4,10,"https://cdn.shopify.com/s/files/1/2487/0212/products/camelot-cabernet-sauvignon-750ml-camelot-cabernet-sauvignon-750ml-29903744598196_600x600.png?v=1628137611",0),
("LB0005","La Colombara Lambrusco Bianco Amabile IGT",457.15,100,"Country of Origin: Italy / 750ml / White Sparking Wine",4,10,"https://cdn.shopify.com/s/files/1/2487/0212/products/la-colombara-lambrusco-bianco-amabile-igt-la-colombara-lambrusco-bianco-amabile-igt-18700683935895_648x648.jpg?v=1614328709",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("LB0006","Budweiser 330ml (bottle)",69.00,100,"Budweiser is a medium-bodied, flavorful, crisp American-style lager. Brewed with the best barley malt and a blend of premium hop varieties, it is an icon of core American values like optimism and celebration.",4,11,"https://cdn.shopify.com/s/files/1/2487/0212/products/budweiser-330ml-bottle-23230433362100_600x600.jpg?v=1627364390",0),
("LB0007","Singha Lager Beer 330ml",93.00,100,"A premium lager beer brewed from the finest ingredients, Singha is a full-bodied 100% barley malt beer that is distinctively rich in taste with strong hop characters. Brewed with three hops: Saaz, Perle and Hallertau.",4,11,"https://cdn.shopify.com/s/files/1/2487/0212/products/singha-lager-beer-330ml-18064856580247_609x609.jpg?v=1614328840",0),
("LB0008","Corona 355ml",121.00,100,"Corona Extra is the number-one selling beer in Mexico and the leading export brand from Mexico. It is best enjoyed with a wedge of lime. This light, dry beer has pleasant malt and hop notes with a round dry finish. 4.6% ABV",4,11,"https://cdn.shopify.com/s/files/1/2487/0212/products/corona-355ml-22820042080436_600x600.jpg?v=1614328626",0),
("LB0009","Chang Beer (bottle)",136.00,100,"Chang Beer (bottle) 320ML",4,11,"https://cdn.shopify.com/s/files/1/2487/0212/products/beer-320ml-chang-19911276298391_600x600.jpg?v=1612460655",0),
("LB0010","Shinga Beer (bottle)",150.00,100,"Shinga Beer (bottle) 320ML",4,11,"https://cdn.shopify.com/s/files/1/2487/0212/products/beer-320ml-singha-19911276232855_600x600.jpg?v=1612460687",0);
INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("LB0011","SanMig Coffee 3in1 Original Strip",53.50,100,"Sugar Free product with Original Coffee 3-in-1 flavor 20g No need to go through traffic just to get your favorite premium food and beverage products!",4,12,"https://cdn.shopify.com/s/files/1/2487/0212/products/sm-coffee-3in1-original-strip-20g-30537805791412_946x1339.jpg?v=1632295212",0),
("LB0012","Top Cafe Brown Delight 25g (10 packs)",71.00,100,"Top Cafe Brown Delight ( Premium Arabica and Robusta blend ) The perfect coffee experience starts with the aroma. Relax and enjoy the caramelly aroma and balanced taste of Top Cafe Brown Delight coffee with Brown sugar, crafted with Premium Arabica and Robusta Blend. Net Weight: 25g x 10 packs",4,12,"https://cdn.shopify.com/s/files/1/2487/0212/products/top-cafe-brown-delight-25g-10-packs-21842822332567_600x600.jpg?v=1612262417",0),
("LB0013","SM Coffee Essenso 25g",100.00,100,"Coffeemix 3 in 1 Premium. No need to go through traffic just to get your favorite premium food and beverage products!",4,12,"https://cdn.shopify.com/s/files/1/2487/0212/products/sm-coffee-essenso-25g-30536875180212_600x600.png?v=1632290898",0),
("LB0014","Caffe Karoma Capsule Soave 5g/pc (6pcs)",252.86,100,"Nespresso compatible espresso soave capsule",4,12,"https://cdn.shopify.com/s/files/1/2487/0212/products/caffe-karoma-capsule-soave-5g-pc-6pcs-18522621837463_500x500.jpg?v=1612455382",0),
("LB0015","Malongo Purs Matins Ground Coffee Tin 250g",699.00,100,"Light and mellow coffee ideal for breakfast and milk-based blends.",4,12,"https://cdn.shopify.com/s/files/1/2487/0212/products/malongo-purs-matins-ground-coffee-tin-250g-malongo-purs-matins-ground-coffee-tin-250g-19186723979415_946x946.jpg?v=1612363065",0);
INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("LB0016","Typhoo Earl Grey 50g",270.00,100,"Typhoo's Earl Grey offers a blend of black teas grown in the exotic and pristine regions of Kenya and Africa, elegantly blended with natural oil of bergamot to give an uplifting, refreshing citrus aroma and flavour.",4,13,"https://cdn.shopify.com/s/files/1/2487/0212/products/typhoo-earl-grey-50g-28026468335796_502x600.jpg?v=1614915783",0),
("LB0017","Yogi Green Tea Super Antioxidant",289.00,100,"Yogi Green Tea Super Antioxidant 16 tea bags",4,13,"https://cdn.shopify.com/s/files/1/0362/8158/1707/products/1f0a92c3cc7a1d816812471e7ff8d7fb_04-23-20_04-47-24_1024x1024.jpg?v=1588411752",0),
("LB0018","Republic Of Tea Get Relaxed",310.00,100,"Republic Of Tea Get Relaxed 6 Tea Bags",4,13,"https://cdn.shopify.com/s/files/1/0362/8158/1707/products/742676608462_01_1024x1024.jpg?v=1630565581",0),
("LB0019","Choice Organic Teas Organic Premium Japanese Green Tea",369.00,100,"Choice Organic Teas Organic Premium Japanese Green Tea 16 tea bags",4,13,"https://cdn.shopify.com/s/files/1/0362/8158/1707/products/047445919214_01_1024x1024.jpg?v=1629775706",0),
("LB0020","Republic Of Tea Pineapple Orange Guava Iced Tea",525.00,100,"Republic Of Tea Pineapple Orange Guava Iced Tea 8 Tea Bags",4,13,"https://cdn.shopify.com/s/files/1/0362/8158/1707/products/republic-of-tea-pineapple-orange-guava-iced-tea-8-tea-bags_1024x1024.jpg?v=1585310269",0);
INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("LB0021","Coca-Cola Coke Zero in Can 330mL",25.75,100,"Coca-Cola Zero Sugar is the perfect drink for when you want all the taste of Coca‑Cola, without the sugar or calories.",4,14,"https://cf.shopee.ph/file/0f2eabad219afa06e8c7f6d86000ad2e",0),
("LB0022","Canada Dry Ginger Ale 355 mL",36.75,100,"Canada Dry Ginger Ale offers refreshingly real ginger taste that's made from real ginger and is caffeine free. Enhance your favorite cocktails with a sparkling splash of Canada Dry or enjoy by itself for crisp, clean refreshment. Creating quality beverages and mixers since 1904, enjoy the best in refreshment with Canada Dry.",4,14,"https://manila-wine.com/media/catalog/product/cache/1/image/700x700/9df78eab33525d08d6e5fb8d27136e95/c/a/canada-dry-ginger-ale.jpg",0),
("LB0023","Sunkist Orange Soda in Can 355mL",39.75,100,"Relax and soak up the taste of the sun with Sunkist Orange Soda. Sunkist Soda beams with bold, sweet orange flavor and refreshes the moment you taste it. Enjoy Sunkist Soda anytime or mix with ice cream for delicious orange floats!",4,14,"https://ph-test-11.slatic.net/p/477f7fd0d09adbabeb1bfd48b368e660.jpg",0),
("LB0024","Zest-O Calamansi Fruit Soda 1.5L",49.25,100,"Enjoy the refreshingly delicious goodness of Zest-O Calamansi Fruit Soda. This fruit soda is made with real fruit juice and contains no caffeine. It offers the combination of the naturally tangy taste of Calamansi, the sweetnes of honey, and the thirst-quenchingly refreshing quality of carbonated soft drinks.",4,14,"https://cdn.shopify.com/s/files/1/0505/7144/4423/products/160337_a73de907-0da1-4384-803b-bba921c50c2a_525x700.jpg?v=1627806975",0),
("LB0025","7 Up Regular 1.5L",58.00,100,"Feel the excitement of lemon, lime and bubbles in every sip of 7 Up! Consisting of the perfect punch of lemon-lime flavors, 7 Up is the most refreshing drink which keeps your mind and body activated.",4,14,"https://cf.shopee.ph/file/0f05a72ecd7e0b9fb60e6836a298f943",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("LB0026","Del Monte Pineapple Orange Juice Drink 240mL",25.25,100,"The healthy goodness of Pineapple gets even better mixed with the sweetness of Orange. Take on this delicious twist in every sip of Del Monte Pineapple Orange Juice Drink!",4,15,"https://www.justgotochef.com/uploads/1524552472-Del%20Monte-Pine%20Orange-240ml-Front%20.jpg",0),
("LB0027","Dole Sparkling Passion Fruit Drink 240mL",27.75,100,"Dole Sparkling Fruit Drinks has the delicious goodness of real fruit juices and what's more exciting is, it captures the zing of a soda! This drink is 100% all natural and has no sugar added.",4,15,"https://cf.shopee.ph/file/24b0e65f176a174682828dd8e38925fe",0),
("LB0028","V8 Original 100% Vegetable Juice 340mL",39.75,100,"This is the original V8 juice that started it all. This uniquely satisfying blend of vegetable juice is an excellent source of Vitamins A & C and helps you get 2 servings of vegetables in every delicious 8 ounce glass for your balanced lifestyle. V8 Original 100% Vegetable Juice has no sugar added and no artificial colors, flavors, or preservatives.",4,15,"https://cdn.shopify.com/s/files/1/0279/1581/2946/products/V8_original_vegetable_Juice_1_700x700.jpg?v=1612594818",0),
("LB0029","Sola Raspberry Iced Tea 472mL",42.75,100,"Sola Raspberry Iced Tea is the perfect combination of sweet and real brewed goodness. Every drop is packed with a refreshing raspberry taste.",4,15,"https://ph-test-11.slatic.net/p/2255063e6334ed30347f33857e55b5fa.jpg",0),
("LB0030","Minute Maid Pulpy Orange Juice 1L",52.75,100,"Naturally-refreshing fruit drink with real fruit juice and real orange pulp which offers deliciously fun and unique sensory experience in the mouth.",4,15,"https://ipcdn.freshop.com/resize?url=https://images.freshop.com/1564405684702543754/14061282b35b3661e69a65d2da92c055_large.png&width=512&type=webp&quality=80",0);
INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("BP0001","Sana Pakora 2 PCs",29.00,100,"Sana Pakora (2 PCs) is affordable for your breakfast, lunch, and dinner.",5,16,"https://cdn.shopify.com/s/files/1/2487/0212/products/sana-pakora-2-pcs-30049452523700_600x439.jpg?v=1629105622",0),
("BP0002","Pan Americano Old Fashion Pandesal 10pcs",79.75,100,"No Filipino breakfast is complete without the classic pandesal! Get that fresh-from-the-oven taste of the pandesal with these Pan America Old-Fashioned Pandesal",5,16,"https://cdn.shopify.com/s/files/1/0402/6456/4888/products/pandesal_1080x.jpg?v=1592329571",0),
("BP0003","Village Gourmet Seasoned Breadsticks",137.00,100,"Village Gourmet Seasoned Breadstick 8 Pieces Per Pack Aromatic and flavorful herbs in soft and light sticks of bread. The perfect side dish for any meal.",5,16,"https://cdn.shopify.com/s/files/1/2487/0212/products/village-gourmet-seasoned-breadsticks-30300417556660_600x600.png?v=1631106147",0),
("BP0004","Sourdough Bread (4pc/pack)",140.00,100,"Go Eats sourdough has just the right amount of tanginess to match its tough exterior. These baked goodies are easily eaten as grilled cheese, with your French caramelized onion soup or even just salt and butter.",5,16,"https://cdn.shopify.com/s/files/1/2487/0212/products/sourdough-bread-30244232855732_946x946.jpg?v=1630660107",0),
("BP0005","Garlic Baguette (4pc/pack)",150.00,100,"Each bite of our new baguette offering comes with savory, garlicky goodness. This favorite can be enjoyed at any time of day, all at your convenience.",5,16,"https://cdn.shopify.com/s/files/1/2487/0212/products/garlic-baguette-30244192518324_600x600.jpg?v=1630659744",0);
INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("BP0006","Salted Decadence Lava Cake",219.00,100,"Salted Decadence Lava Cake 350g",5,17,"https://cdn.shopify.com/s/files/1/0244/3401/6290/products/salted-decadence-lava-cake-943321_900x.jpg?v=1633468709",0),
("BP0007","Assorted Lava Cakes",250.00,100,"Assorted Lava Cakes (1pack)",5,17,"https://cdn.shopify.com/s/files/1/0244/3401/6290/products/goeats-assorted-lava-cakes-505943_900x.jpg?v=1626331583",0),
("BP0008","Banana Chocolate Chip and Walnut Loaf",250.00,100,"Size: 430 grams",5,17,"https://cdn.shopify.com/s/files/1/0244/3401/6290/products/banana-chocolate-chip-and-walnut-loaf-481244_900x.jpg?v=1626937193",0),
("BP0009","Lemon Almond Loaf",350.00,100,"Size: 440 grams",5,17,"https://cdn.shopify.com/s/files/1/0244/3401/6290/products/lemon-almond-loaf-960175_900x.jpg?v=1626937359",0),
("BP0010","Pumpkin Pie 880g",700.00,100,"Size: 880g",5,17,"https://cdn.shopify.com/s/files/1/0362/8158/1707/products/ChristmasPumpkinPieLarge_1024x1024.jpg?v=1637227156",0);
INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("BP0011","Palets Bretons Cookies 5 pieces",200.00,100,"Fill your empty cookie jar with the incredibly crispy, buttery and melt-in-your-mouth French butter biscuits. Not too sweet, slightly salty with sea salt, this set of 5 cookies is irresistible and perfect for teatime!  All-natural ingredients. Contains gluten, dairy, and eggs.",5,18,"https://cdn.shopify.com/s/files/1/0362/8158/1707/products/PaletBretonCookies_1024x1024.jpg?v=1597109779",0),
("BP0012","Gluten-Free Almond Choco Cookies 3 pieces",285.00,100,"This set of 3 gluten-free and sugar-free cookies will satisfy your craving. Made with oats thick rolled, raw almonds, premium milk chocolate and sweetened with agave, it's best served with a tea or coffee.",5,18,"https://cdn.shopify.com/s/files/1/0362/8158/1707/products/Gluten-FreeAlmondChocoCookies_1024x1024.jpg?v=1597109716",0),
("BP0013","Gluten-Free Carrot Walnut Muffins 3 pcs",330.00,100,"Set of 3 Carrot Muffins: Made with organic carrots, whole grain brown rice flour, and crunchy walnuts, these Carrot Muffins are perfect for a healthier tea time snack.",5,18,"https://cdn.shopify.com/s/files/1/0362/8158/1707/products/Gluten-FreeCarrotWalnutMuffins3pcs_1024x1024.jpg?v=1591320565",0),
("BP0014","Gluten-Free Double Chocolate Muffins 3 pcs",330.00,100,"Set of 3 Double Chocolate Muffins: Irresistibly moist and fluffy, the Gluten-Free Double Chocolate Muffins have an intense chocolate flavor, that goes perfectly with black coffee. Made with Valrhona dark and milk chocolate.",5,18,"https://cdn.shopify.com/s/files/1/0362/8158/1707/products/Gluten-FreeDoubleChocolateMuffins3pcs_1024x1024.jpg?v=1591342096",0),
("BP0015","Gluten-Free Keto Cinnamon Coffee Muffins 3 pcs",525.00,100,"Set of 3 Keto Muffins: Indulge yourself with sugar-free and low-carb coffee muffins, flavored with a touch of cinnamon. With a total of 28.7g fat, 7.8g protein, and only 3% total carbs, these heavenly treats are safe for your keto diet.",5,18,"https://cdn.shopify.com/s/files/1/0362/8158/1707/products/Gluten-FreeKetoCinnamonCoffeeMuffins3pcs_1024x1024.jpg?v=1591320608",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("DR0001","Star Margarine Classic 100g",29.50,100,"Classic margarine taste that is both delicious and nutritious. - Fortified with Vitamins A, B, D, E and other vitamins and nutrients. - Best used for Mixing on Rice, Spreading on bread, and Baking.",6,19,"https://cdn.shopify.com/s/files/1/2487/0212/products/star-margarine-classic-100g-30538686988468_946x946.jpg?v=1632300256",0),
("DR0002","Baker's Best 225G",47.00,100,"No need to go through traffic just to get your favorite premium food and beverage products!",6,19,"https://cdn.shopify.com/s/files/1/2487/0212/products/baker-s-best-30424201560244_946x571.jpg?v=1631697793",0),
("DR0003","Dari Creme Classic",54.00,100,"Dari Creme Classic 200G",6,19,"https://cdn.shopify.com/s/files/1/2487/0212/products/dari-creme-classic-30424187699380_946x571.jpg?v=1631697791",0),
("DR0004","Dari Creme Buttermilk",54.00,100,"Dari Creme Buttermilk 200g",6,19,"https://cdn.shopify.com/s/files/1/2487/0212/products/dari-creme-buttermilk-30424211194036_946x571.jpg?v=1631697796",0),
("DR0005","Magnolia Butter-licious Salted",70.00,100,"Magnolia Butter-licious Salted 20g",6,19,"https://cdn.shopify.com/s/files/1/2487/0212/products/magnolia-butter-licious-200g-30770643894452_946x528.jpg?v=1633413188",0);
INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("DR0006","Magnolia Cheezee Regular 165G",45.50,100,"Busog sa sarap! Creamy, Easy to slice and grate, at maspinasarap!",6,20,"https://cdn.shopify.com/s/files/1/2487/0212/products/magnolia-cheezee-regular-165g-30538619125940_946x756.png?v=1632299710",0),
("DR0007","Lactima Processed Cheese Edam",117.00,100,"Lactima Processed Cheese Edam (8 Slices) 130g",6,20,"https://cdn.shopify.com/s/files/1/2487/0212/products/lactima-processed-cheese-edam-8-slices-lactima-processed-cheese-edam-8-slices-130g-19724788465815_600x600.jpg?v=1612375969",0),
("DR0008","Pinkie's Farm Kesong Puti (White Cheese) 200g",182.00,100,"Pasteurized Milk, Rennet, Coarse Sea Salt",6,20,"https://cdn.shopify.com/s/files/1/2487/0212/products/pinkie-s-farm-kesong-puti-white-cheese-200g-29789950214324_600x600.jpg?v=1627070428",0),
("DR0009","Magnolia Quickmelt 440G",205.00,100,"Has milky cheddar taste, fine creamy texture with excellent melting properties, that makes our  dishes more special.",6,20,"https://cdn.shopify.com/s/files/1/2487/0212/products/magnolia-quickmelt-440g-30538884710580_500x500.png?v=1632301159",0),
("DR0010","Real Formaggi Edamer 250g",248.00,100,"Dutch-type eyed semi-hard cheese. Taste: Mild, slightly salty or nutty",6,20,"https://cdn.shopify.com/s/files/1/2487/0212/products/real-formaggi-edamer-250g-23793383932084_600x600.jpg?v=1627093370",0);
INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("DR0011","Magnolia Full Cream Milk 200ML",23.00,100,"Recombined Full Cream Milk. UHT-Processed long life.",6,21,"https://cf.shopee.ph/file/45474da8e208bb5d83c42cd15e916c44",0),
("DR0012","Magnolia Fresh Milk 250ML",35.30,100,"Premium, pure, and farm-fresh cow's milk. Nutritious never tasted so delicious!",6,21,"https://cdn.shopify.com/s/files/1/0280/7126/4308/products/67de569404c4b8e3852d6713dea1b82d_jpg_720x720q80_jpg_720x.png?v=1587541345",0),
("DR0013","Magnolia Low Fat Milk 1L",88.00,100,"Pure, fresh cow's milk. Premium creamy taste with less than 2% fat. High in Calcium. Imported from Germany.",6,21,"https://cdn.shopify.com/s/files/1/0280/7126/4308/products/62a1b560fdcef4f3cf9cd74f2677c9d5_530x@2x.jpg?v=1587967558",0),
("DR0014","Whole Milk 1L",126.00,100,"Whole Milk 1L",6,21,"https://cdn.shopify.com/s/files/1/2487/0212/products/whole-milk-whole-milk-1l-23512129503412_600x600.jpg?v=1612231058",0),
("DR0015","Bonsoy Almond Milk",300.00,100,"Creamy texture that can achieve silky microfoam for the perfect latte. Pairs perfectly with coffee, smoothies and shakes. Can be used in place of dairy milk across multiple uses such as baking.",6,21,"https://cdn.shopify.com/s/files/1/2487/0212/products/bonsoy-almond-milk-29895480410292_600x600.jpg?v=1628169105",0);

INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("DP0001","Old Swiss Inn Pocket Pie",146.00,100,"Instructions: 1. From freezer, microwave for 2 minutes then toast in an oven toaster for 2 minutes. 2. Serve warm.",7,22,"https://cdn.shopify.com/s/files/1/2487/0212/products/beef-and-mushroom-100g-old-swiss-inn-pocket-pie-29493403254964_600x600.jpg?v=1625194478",0),
("DP0002","Tonkotsu Ramen Set",242.00,100,"Our DIY ramen kit--comes with our handmade noodles, pork bone broth,minced pork, and leeks. Frozen/Ready to cook.",7,22,"https://cdn.shopify.com/s/files/1/2487/0212/products/tonkotsu-ramen-set-29893425201332_600x600.jpg?v=1628185671",0),
("DP0003","Dong Bao Pork",346.00,100,"Slow-braised pork belly with Shaoxing and spices in sweet dark soy sauce Each Kit contains 1 Bag of 3 slices of pork belly, Chinese sausage and sauce 1 Bag of steamed rice and bok choy",7,22,"https://cdn.shopify.com/s/files/1/2487/0212/products/dong-bao-pork-18599827243159_600x600.png?v=1612351780",0),
("DP0004","General's Lechon Bicol Express",388.00,100,"General's Lechon Bicol Express (boilable bag)",7,22,"https://cdn.shopify.com/s/files/1/2487/0212/products/general-s-lechon-bicol-express-29822654709940_600x600.jpg?v=1628032680",0),
("DP0005","Kare-Kare",414.00,100,"Braised beef oxtail in peanut sauce, served with traditional condiments and steamed rice It is a popular dish in the Philippines served during special occasions. Each Kit contains: 1 Bag of Oxtail. beef tripe and kare kare sauce 1 Bag of steamed rice 1 Bag of bok choy, eggplant, string beans 1 2oz container of Bagoong",7,22,"https://cdn.shopify.com/s/files/1/2487/0212/products/kare-kare-18599835205783_600x600.png?v=1612355335",0);
INSERT INTO product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_PRICE,PRODUCT_STOCK,PRODUCT_DESCRIPTION,CATEGORY_ID,SUBCATEGORY_ID,PRODUCT_IMAGE,PRODUCT_STATUS)
VALUES
("DP0006","Negroni Wurstel Golosino 100 g pack",67.00,100," Is a kind of thin sausage made of finely chopped pork meat typical of Germany, Austria and Trentino (Northen Italy).",7,23,"https://cdn.shopify.com/s/files/1/2487/0212/products/negroni-wurstel-golosino-100-g-pack-30555569815732_600x600.jpg?v=1632362529",0),
("DP0007","Salchichon Bellota Txanton - Sliced 80g",257.00,100,"80g of acorn-fed iberian pork sausage highly-seasoned with salt and black pepper. Curing period: 4-5 months.",7,23,"https://cdn.shopify.com/s/files/1/2487/0212/products/salchichon-bellota-txanton-sliced-80g-23681506050228_600x600.jpg?v=1628184451",0),
("DP0008","Chorizo Bellota Txanton - Sliced 80g",257.00,100,"80g of acorn-fed iberian pork sausage highly-seasoned with cayenne pepper, sweet papprika, salt and garlic. Curing period: 4-5 months.",7,23,"https://cdn.shopify.com/s/files/1/2487/0212/products/chorizo-bellota-txanton-sliced-80g-29831530709172_600x600.jpg?v=1627995610",0),
("DP0009","Segata Prosciutto Cotto Spalla 250g",296.00,100,"Steamed pork shoulder ham.",7,23,"https://cdn.shopify.com/s/files/1/2487/0212/products/segata-prosciutto-cotto-spalla-250g-29896796995764_600x600.jpg?v=1628178689",0),
("DP0010","Menatti Mortadella 250g",386.00,100,"Sausage made of finely ground heat-cured pork.",7,23,"https://cdn.shopify.com/s/files/1/2487/0212/products/menatti-mortadella-250g-29896984035508_600x600.jpg?v=1628147327",0);

--user:admin1@email.com password: = admin
INSERT INTO accounts(USER_EMAIL, USER_PASSWORD, USER_NAME, PHONE, ADDRESS, ACCOUNT_ROLE) VALUES ("admin1@email.com","$2a$12$FtK2yWqrj/xAf983cdVDMORGPtsixqMvBC5bagafzu2AoEsjdSvy6", "admin1", 9876543, "Quezon City", "ROLE_ADMIN");


-- SELECT * FROM product;
-- SELECT * FROM accounts;
-- SELECT * FROM category;
-- SELECT * FROM subcategory;
-- SELECT * FROM order_line;
-- SELECT * FROM orders;

-- SELECT * FROM product WHERE MATCH(PRODUCT_NAME,PRODUCT_DESCRIPTION)
--             AGAINST ("beef");

-- alter table product modify PRODUCT_PRICE decimal(6,2) not null;

-- drop table order_line;
-- drop table orders;
-- drop table accounts
-- drop schema onlinestore