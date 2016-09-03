create or replace PROCEDURE ADDPRODUCT 
(
pProductCategory PRODUCT."Product_Category"%type,
pProductDescription PRODUCT.PRODUCT_DESCRIPTION%type,
pProductName PRODUCT.PRODUCT_NAME%type,
pProductStock PRODUCT.PRODUCT_STOCK%type
)
AS 
BEGIN
  INSERT INTO PRODUCT(PRODUCT_ID, PRODUCT."Product_Category", PRODUCT_DESCRIPTION, PRODUCT_NAME, PRODUCT_STOCK)
    VALUES(PRODUCT_ID_SEQ.nextval, pProductCategory, pProductDescription, pProductName, pProductStock);

EXCEPTION
  WHEN dup_val_on_index THEN
    dbms_output.put_line('This product already exists');
END;