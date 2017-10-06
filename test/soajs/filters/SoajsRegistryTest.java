/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soajs.filters;

import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Etienne
 */
public class SoajsRegistryTest {
    
    private static Thread controllerThread;
    
    public static JSONObject data = new JSONObject("{\"dashboard\":{\"profileOnly\":false,\"environment\":\"dashboard\",\"serviceConfig\":{\"agent\":{\"topologyDir\":\"/opt/soajs/\"},\"cors\":{\"headers\":\"key,soajsauth,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization\",\"maxage\":1728000,\"credentials\":\"true\",\"methods\":\"GET,HEAD,PUT,PATCH,POST,DELETE\",\"origin\":\"*\",\"enabled\":true},\"awareness\":{\"maxLogCount\":5,\"autoRegisterService\":true,\"autoRelaodRegistry\":3600000,\"healthCheckInterval\":5000},\"cookie\":{\"secret\":\"YP Store cookie security secret phrase\"},\"session\":{\"rolling\":false,\"cookie\":{\"path\":\"/\",\"maxAge\":null,\"httpOnly\":true,\"secure\":false},\"saveUninitialized\":false,\"name\":\"soajsID\",\"secret\":\"YP Store session security phrase\",\"resave\":false,\"unset\":\"keep\"},\"logger\":{\"formatter\":{\"outputMode\":\"long\"},\"src\":true,\"level\":\"debug\"},\"ports\":{\"controller\":4000,\"randomInc\":100,\"maintenanceInc\":1000},\"oauth\":{\"grants\":[\"password\",\"refresh_token\"],\"debug\":false,\"refreshTokenLifetime\":1209600,\"accessTokenLifetime\":7200},\"key\":{\"password\":\"YP store dashboard secret key password\",\"algorithm\":\"aes256\"}},\"custom\":{},\"name\":\"dashboard\",\"daemons\":{\"catalogdaemon\":{\"port\":4511,\"versions\":{\"1\":{\"jobs\":{\"buildCatalog\":{}}}},\"group\":\"daemon\"},\"orderDaemon\":{\"port\":4502,\"versions\":{\"1\":{\"jobs\":{\"createSubOrders\":{}}}},\"group\":\"No Group Daemon\"},\"cleaner\":{\"port\":4513,\"versions\":{\"1\":{\"jobs\":{\"cleanVersions\":{\"l\":\"BlackList Check and Version Cleaning Daemon\"}}}},\"group\":\"No Group Daemon\"},\"migrator\":{\"port\":4520,\"versions\":{\"1\":{\"jobs\":{\"migrateData\":{\"l\":\"Data migration between environments\"}}}},\"group\":\"No Group Daemon\"},\"merchantsCreator\":{\"port\":4512,\"versions\":{\"1\":{\"jobs\":{\"createMerchants\":{\"l\":\"Merchants Creator\"}}}},\"group\":\"No Group Daemon\"}},\"tenantMetaDB\":{\"urac\":{\"cluster\":\"cluster1\",\"streaming\":null,\"servers\":[{\"port\":27017,\"host\":\"127.0.0.1\"}],\"credentials\":null,\"prefix\":\"STOR_\",\"URLParam\":{\"wtimeoutMS\":0,\"slaveOk\":true,\"socketTimeoutMS\":0,\"connectTimeoutMS\":0,\"maxPoolSize\":5},\"name\":\"#TENANT_NAME#_urac\",\"extraParam\":{\"server\":{\"socketOptions\":{\"autoReconnect\":false}},\"db\":{\"bufferMaxEntries\":0,\"native_parser\":true}}}},\"resources\":{\"test\":{}},\"timeLoaded\":1507205730796,\"deployer\":{\"container\":{\"coreos\":{},\"selected\":\"docker.socket\",\"docker\":{\"joyent\":{\"port\":2376,\"host\":\"us-east-1.docker.joyent.com\"},\"socket\":{\"socketPath\":\"/var/run/docker.sock\"},\"selected\":\"socket\",\"boot2docker\":{\"port\":2376,\"host\":\"192.168.59.103\"}}},\"cloud\":{\"joyent\":{},\"gCloud\":{},\"AWS\":{},\"azure\":{}},\"type\":\"manual\"},\"services\":{\"controller\":{\"authorization\":true,\"port\":4000,\"hosts\":{\"1\":[\"127.0.0.1\"],\"latest\":1},\"requestTimeoutRenewal\":null,\"maxPoolSize\":100,\"requestTimeout\":30,\"group\":\"controller\"},\"knowledgebase\":{\"port\":4131,\"versions\":{\"1\":{\"urac_Profile\":false,\"apis\":[{\"v\":\"/health\",\"l\":\"Health Check\",\"m\":\"get\",\"group\":\"Health\"},{\"v\":\"/owner/methods/getConfig\",\"l\":\"Get Method Config\",\"m\":\"get\",\"group\":\"Guest\"},{\"v\":\"/selfserve/methods/get\",\"l\":\"Get Methods\",\"m\":\"get\",\"group\":\"Guest\"},{\"v\":\"/owner/merchants/pending/changeStatus\",\"l\":\"Change Pending Merchant Status\",\"m\":\"get\",\"group\":\"Owner\"},{\"groupMain\":true,\"v\":\"/migrate/data/list\",\"l\":\"List data to be migrated\",\"m\":\"get\",\"group\":\"Data Migration\"},{\"v\":\"/migrate/data/remove\",\"l\":\"Remove registered data from migration\",\"m\":\"get\",\"group\":\"Data Migration\"},{\"groupMain\":true,\"v\":\"/owner/merchants/pending/list\",\"l\":\"List Pending Merchants\",\"m\":\"get\",\"group\":\"Owner\"},{\"v\":\"/owner/merchants/pending/delete\",\"l\":\"Delete Pending Merchant\",\"m\":\"get\",\"group\":\"Owner\"},{\"v\":\"/owner/merchants\",\"l\":\"List Merchants\",\"m\":\"get\",\"group\":\"Owner\"},{\"v\":\"/owner/merchants/:id\",\"l\":\"Get One Merchant\",\"m\":\"get\",\"group\":\"Owner\"},{\"v\":\"/owner/merchants/:id/pos\",\"l\":\"List Merchants POS\",\"m\":\"get\",\"group\":\"Owner\"},{\"v\":\"/tenant/merchants\",\"l\":\"List Merchants\",\"m\":\"get\",\"group\":\"Tenant\"},{\"v\":\"/tenant/merchants/:id\",\"l\":\"Get One Merchant\",\"m\":\"get\",\"group\":\"Tenant\"},{\"v\":\"/tenant/merchants/:id/pos\",\"l\":\"List Merchants POS\",\"m\":\"get\",\"group\":\"Tenant\"},{\"v\":\"/owner/products\",\"l\":\"List Products\",\"m\":\"get\",\"group\":\"Owner\"},{\"v\":\"/owner/products/:id\",\"l\":\"Get Product\",\"m\":\"get\",\"group\":\"Owner\"},{\"v\":\"/owner/products/variations\",\"l\":\"Get Product Variations\",\"m\":\"get\",\"group\":\"Owner\"},{\"v\":\"/tenant/products\",\"l\":\"List Products\",\"m\":\"get\",\"group\":\"Tenant\"},{\"v\":\"/tenant/products/:id\",\"l\":\"Get Product\",\"m\":\"get\",\"group\":\"Tenant\"},{\"v\":\"/tenant/products/variations\",\"l\":\"Get Product Variations\",\"m\":\"get\",\"group\":\"Tenant\"},{\"v\":\"/products/search\",\"l\":\"Search for a Product\",\"m\":\"get\",\"group\":\"Products\"},{\"v\":\"/product/headings\",\"l\":\"Get All Headings\",\"m\":\"get\",\"group\":\"Products\"},{\"v\":\"/product/markets\",\"l\":\"Get Markets\",\"m\":\"get\",\"group\":\"Products\"},{\"v\":\"/product/verticals\",\"l\":\"Get All Verticals\",\"m\":\"get\",\"group\":\"Products\"},{\"v\":\"/product/sensitiveHeadings\",\"l\":\"Get All Sensitive Headings\",\"m\":\"get\",\"group\":\"Products\"},{\"v\":\"/product/serviceArea\",\"l\":\"Get All Service Areas\",\"m\":\"get\",\"group\":\"Products\"},{\"v\":\"/product/availableProducts\",\"l\":\"Get All Available Products\",\"m\":\"get\",\"group\":\"Products\"},{\"v\":\"/owner/promoCodes\",\"l\":\"Get All Promo Codes\",\"m\":\"get\",\"group\":\"Promo Code\"},{\"v\":\"/owner/promoCodes/:id\",\"l\":\"Get A Promo Code\",\"m\":\"get\",\"group\":\"Promo Code\"},{\"v\":\"/product/bundles\",\"l\":\"Get All Bundles\",\"m\":\"get\",\"group\":\"Discount Engine\"},{\"v\":\"/product/bundles/:id\",\"l\":\"Get A Bundle\",\"m\":\"get\",\"group\":\"Discount Engine\"},{\"v\":\"/product/discounts\",\"l\":\"Get All discounts\",\"m\":\"get\",\"group\":\"Discount Engine\"},{\"v\":\"/product/discounts/:id\",\"l\":\"Get A Discount\",\"m\":\"get\",\"group\":\"Discount Engine\"},{\"v\":\"/selfserve/merchants/validateAddress\",\"l\":\"Validate Address\",\"m\":\"post\",\"group\":\"Guest\"},{\"v\":\"/migrate/data/register\",\"l\":\"Register new data to migrate\",\"m\":\"post\",\"group\":\"Data Migration\"},{\"groupMain\":true,\"v\":\"/selfserve/merchants/join\",\"l\":\"Merchant Join\",\"m\":\"post\",\"group\":\"Guest\"},{\"v\":\"/owner/merchants\",\"l\":\"Add Merchant\",\"m\":\"post\",\"group\":\"Owner\"},{\"v\":\"/owner/merchants/:id/configure\",\"l\":\"Configure Merchant\",\"m\":\"post\",\"group\":\"Owner\"},{\"v\":\"/owner/merchants/:id/overrideProfile\",\"l\":\"Merchant Override Profile\",\"m\":\"post\",\"group\":\"Owner\"},{\"v\":\"/owner/merchants/:id/pos\",\"l\":\"Add Merchant POS\",\"m\":\"post\",\"group\":\"Owner\"},{\"v\":\"/tenant/merchants\",\"l\":\"Add Merchant\",\"m\":\"post\",\"group\":\"Tenant\"},{\"v\":\"/tenant/merchants/:id/configure\",\"l\":\"Configure Merchant\",\"m\":\"post\",\"group\":\"Tenant\"},{\"v\":\"/tenant/merchants/:id/overrideProfile\",\"l\":\"Merchant Override Profile\",\"m\":\"post\",\"group\":\"Tenant\"},{\"v\":\"/tenant/merchants/:id/pos\",\"l\":\"Add Merchant POS\",\"m\":\"post\",\"group\":\"Tenant\"},{\"v\":\"/owner/products\",\"l\":\"Add Product\",\"m\":\"post\",\"group\":\"Owner\"},{\"v\":\"/owner/products/saveDraft\",\"l\":\"Save Product as draft\",\"m\":\"post\",\"group\":\"Owner\"},{\"v\":\"/tenant/products\",\"l\":\"Add Product\",\"m\":\"post\",\"group\":\"Tenant\"},{\"v\":\"/tenant/products/saveDraft\",\"l\":\"Save Product as draft\",\"m\":\"post\",\"group\":\"Tenant\"},{\"v\":\"/tenant/products/updateStkQty\",\"l\":\"Update Product Stock Quantity\",\"m\":\"post\",\"group\":\"Tenant\"},{\"v\":\"/tenant/products/push\",\"l\":\"Push Product\",\"m\":\"post\",\"group\":\"Tenant\"},{\"v\":\"/products/addImage\",\"l\":\"Add Image File\",\"m\":\"post\",\"group\":\"Products\"},{\"v\":\"/products/addImageUrl\",\"l\":\"Add Image URL\",\"m\":\"post\",\"group\":\"Products\"},{\"v\":\"/products/applyVariations\",\"l\":\"Apply images to Product Variations\",\"m\":\"post\",\"group\":\"Products\"},{\"v\":\"/product/bundles\",\"l\":\"Create Bundle\",\"m\":\"post\",\"group\":\"Discount Engine\"},{\"v\":\"/product/bundlesDraft\",\"l\":\"Save Bundle as draft\",\"m\":\"post\",\"group\":\"Discount Engine\"},{\"v\":\"/owner/promoCodes\",\"l\":\"Create a Promo Code\",\"m\":\"post\",\"group\":\"Promo Code\"},{\"v\":\"/product/discounts\",\"l\":\"Create a Discount\",\"m\":\"post\",\"group\":\"Discount Engine\"},{\"v\":\"/owner/merchants/:id\",\"l\":\"Edit Merchant\",\"m\":\"put\",\"group\":\"Owner\"},{\"v\":\"/owner/merchants/:id/status\",\"l\":\"Change Merchant Status\",\"m\":\"put\",\"group\":\"Owner\"},{\"v\":\"/owner/merchants/:id/pos/:posId\",\"l\":\"Edit Merchant's POS\",\"m\":\"put\",\"group\":\"Owner\"},{\"v\":\"/owner/merchants/:id/pos/:posId/status\",\"l\":\"Change POS Status\",\"m\":\"put\",\"group\":\"Owner\"},{\"v\":\"/owner/products/:id\",\"l\":\"Edit Product\",\"m\":\"put\",\"group\":\"Owner\"},{\"v\":\"/owner/products/updateStock\",\"l\":\"Update Product Stock\",\"m\":\"put\",\"group\":\"Owner\"},{\"v\":\"/owner/products/:id/status\",\"l\":\"Change Product Status\",\"m\":\"put\",\"group\":\"Owner\"},{\"v\":\"/tenant/merchants/:id\",\"l\":\"Edit Merchant\",\"m\":\"put\",\"group\":\"Tenant\"},{\"v\":\"/tenant/merchants/:id/status\",\"l\":\"Change Merchant Status\",\"m\":\"put\",\"group\":\"Tenant\"},{\"v\":\"/tenant/merchants/:id/pos/:posId\",\"l\":\"Edit Merchant's POS\",\"m\":\"put\",\"group\":\"Tenant\"},{\"v\":\"/tenant/merchants/:id/pos/:posId/status\",\"l\":\"Change POS Status\",\"m\":\"put\",\"group\":\"Tenant\"},{\"v\":\"/tenant/products/:id\",\"l\":\"Edit Product\",\"m\":\"put\",\"group\":\"Tenant\"},{\"v\":\"/tenant/products/updateStock\",\"l\":\"Update Product Stock\",\"m\":\"put\",\"group\":\"Tenant\"},{\"v\":\"/tenant/products/:id/status\",\"l\":\"Change Product Status\",\"m\":\"put\",\"group\":\"Tenant\"},{\"v\":\"/product/bundles/:id\",\"l\":\"Edit Bundle\",\"m\":\"put\",\"group\":\"Discount Engine\"},{\"v\":\"/product/bundlesDraft\",\"l\":\"Save Bundle as draft\",\"m\":\"put\",\"group\":\"Discount Engine\"},{\"v\":\"/product/bundles/:id/status\",\"l\":\"Change Bundle Status\",\"m\":\"put\",\"group\":\"Discount Engine\"},{\"v\":\"/owner/promoCodes/:id\",\"l\":\"Edit a Promo Code\",\"m\":\"put\",\"group\":\"Promo Code\"},{\"v\":\"/owner/promoCodes/:id/status\",\"l\":\"Change Promo Code Status\",\"m\":\"put\",\"group\":\"Promo Code\"},{\"v\":\"/product/discounts/:id\",\"l\":\"Edit a Discount\",\"m\":\"put\",\"group\":\"Discount Engine\"},{\"v\":\"/product/discounts/:id/status\",\"l\":\"Change Discount Status\",\"m\":\"put\",\"group\":\"Discount Engine\"},{\"v\":\"/owner/merchants/:id\",\"l\":\"Delete Merchant\",\"m\":\"delete\",\"group\":\"Owner\"},{\"v\":\"/owner/merchants/:id/pos/:posId\",\"l\":\"Delete Merchant's POS\",\"m\":\"delete\",\"group\":\"Owner\"},{\"v\":\"/owner/products/:id\",\"l\":\"Delete Product\",\"m\":\"delete\",\"group\":\"Owner\"},{\"v\":\"/tenant/merchants/:id\",\"l\":\"Delete Merchant\",\"m\":\"delete\",\"group\":\"Tenant\"},{\"v\":\"/tenant/merchants/:id/pos/:posId\",\"l\":\"Delete Merchant's POS\",\"m\":\"delete\",\"group\":\"Tenant\"},{\"v\":\"/tenant/products/:id\",\"l\":\"Delete Product\",\"m\":\"delete\",\"group\":\"Tenant\"},{\"v\":\"/migrate/data\",\"l\":\"Remove registered data from migration\",\"m\":\"delete\",\"group\":\"Data Migration\"},{\"v\":\"/product/bundles/:id\",\"l\":\"Delete A Bundle\",\"m\":\"delete\",\"group\":\"Discount Engine\"},{\"v\":\"/product/discounts/:id\",\"l\":\"Delete A Discount\",\"m\":\"delete\",\"group\":\"Discount Engine\"},{\"v\":\"/owner/promoCodes/:id\",\"l\":\"Delete A Promo Code\",\"m\":\"delete\",\"group\":\"Promo Code\"}],\"extKeyRequired\":true,\"urac\":false,\"provision_ACL\":false,\"oauth\":true,\"urac_ACL\":false}},\"extKeyRequired\":true,\"hosts\":{\"1\":[\"127.0.0.1\"],\"latest\":1},\"requestTimeoutRenewal\":5,\"version\":1,\"oauth\":true,\"requestTimeout\":60,\"group\":\"store\"},\"catalog\":{\"port\":4134,\"versions\":{\"1\":{\"urac_Profile\":false,\"apis\":[{\"v\":\"/health\",\"l\":\"Health Check\",\"m\":\"get\",\"group\":\"Health\"},{\"v\":\"/categories\",\"l\":\"Get Categories\",\"m\":\"get\",\"group\":\"Front end\"},{\"v\":\"/products\",\"l\":\"List Products\",\"m\":\"get\",\"group\":\"Front end\"},{\"v\":\"/product/:serial\",\"l\":\"Get Product Info\",\"m\":\"get\",\"group\":\"Front end\"},{\"v\":\"/product/:serial/addOns\",\"l\":\"Get Product AddOns\",\"m\":\"get\",\"group\":\"Front end\"},{\"v\":\"/featuredProducts\",\"l\":\"Get Featured Products\",\"m\":\"get\",\"group\":\"Front end\"},{\"v\":\"/merchant/:id\",\"l\":\"Get Merchant Info\",\"m\":\"get\",\"group\":\"Front end\"},{\"v\":\"/bundles\",\"l\":\"Get Bundles\",\"m\":\"get\",\"group\":\"Front end\"},{\"v\":\"/bundle/:serial\",\"l\":\"Get Bundle Info\",\"m\":\"get\",\"group\":\"Front end\"},{\"v\":\"/owner/catalog/merchants\",\"l\":\"Returns all merchants per tenant\",\"m\":\"get\",\"group\":\"Profile Management\"},{\"v\":\"/owner/catalog/profiles\",\"l\":\"List the catalog profiles for the owner to configure\",\"m\":\"get\",\"group\":\"Profile Management\"},{\"v\":\"/owner/catalog/build\",\"l\":\"Build Catalog\",\"m\":\"get\",\"group\":\"Profile Management\"},{\"v\":\"/configure/:serial\",\"l\":\"Configure Product Info\",\"m\":\"post\",\"group\":\"Front end\"},{\"v\":\"/bundles\",\"l\":\"Get Bundles\",\"m\":\"post\",\"group\":\"Front end\"},{\"v\":\"/products\",\"l\":\"Get Products with Search\",\"m\":\"post\",\"group\":\"Front end\"},{\"v\":\"/search\",\"l\":\"Search Products and Bundles\",\"m\":\"post\",\"group\":\"Front end\"},{\"v\":\"/productsDetails\",\"l\":\"Products Details\",\"m\":\"post\",\"group\":\"Front end\"},{\"v\":\"/treats\",\"l\":\"Get Treats with taxnomoies\",\"m\":\"post\",\"group\":\"Front end\"},{\"v\":\"/merchants\",\"l\":\"Get Merchants\",\"m\":\"post\",\"group\":\"Front end\"},{\"v\":\"/owner/catalog/profiles\",\"l\":\"Add a new catalog profile configuration\",\"m\":\"post\",\"group\":\"Profile Management\"},{\"v\":\"/owner/catalog/profiles/:id\",\"l\":\"Update the catalog profile configuration\",\"m\":\"put\",\"group\":\"Profile Management\"},{\"v\":\"/owner/catalog/profiles/:id\",\"l\":\"Deletes the catalog profile configuration\",\"m\":\"delete\",\"group\":\"Profile Management\"}],\"extKeyRequired\":true,\"urac\":false,\"provision_ACL\":false,\"oauth\":false,\"urac_ACL\":false}},\"extKeyRequired\":true,\"hosts\":{\"1\":[\"127.0.0.1\"],\"latest\":1},\"requestTimeoutRenewal\":5,\"version\":1,\"oauth\":false,\"requestTimeout\":30,\"group\":\"store\"},\"tidbit\":{\"port\":4382,\"versions\":{\"1\":{\"urac_Profile\":false,\"apis\":[{\"v\":\"/authorization\",\"l\":\"Get the authorization value\",\"m\":\"get\"},{\"v\":\"/token\",\"l\":\"Create Token\",\"m\":\"post\"},{\"v\":\"/accessToken/:token\",\"l\":\"Delete Access Token\",\"m\":\"delete\"},{\"v\":\"/refreshToken/:token\",\"l\":\"Delete Refresh Token\",\"m\":\"delete\"},{\"v\":\"/tokens/user/:userId\",\"l\":\"Delete all Tokens for this User\",\"m\":\"delete\"},{\"v\":\"/tokens/tenant/:clientId\",\"l\":\"Delete all Tokens for this Client\",\"m\":\"delete\"}],\"extKeyRequired\":true,\"urac\":false,\"provision_ACL\":false,\"oauth\":true,\"urac_ACL\":false}},\"extKeyRequired\":true,\"hosts\":{\"1\":[\"127.0.0.1\"],\"latest\":1},\"requestTimeoutRenewal\":5,\"version\":1,\"oauth\":true,\"requestTimeout\":30,\"group\":\"SOAJS Core Services\"},\"shoppingcart\":{\"port\":4132,\"versions\":{\"1\":{\"apis\":[{\"groupMain\":true,\"v\":\"/dashboard/getCarts\",\"l\":\"Get All Carts\",\"m\":\"get\",\"group\":\"Dashboard Owner\"},{\"v\":\"/mergeCart\",\"l\":\"Merge Cart\",\"m\":\"get\",\"group\":\"User Frontend\"},{\"groupMain\":true,\"v\":\"/getCart\",\"l\":\"Get Cart\",\"m\":\"get\",\"group\":\"User Frontend\"},{\"v\":\"/applyPromoCode\",\"l\":\"Add Promo to Cart\",\"m\":\"get\",\"group\":\"User Frontend\"},{\"v\":\"/removePromoCode\",\"l\":\"Remove Promo from Cart\",\"m\":\"get\",\"group\":\"User Frontend\"},{\"v\":\"/emptyCart\",\"l\":\"Empty Cart\",\"m\":\"get\",\"group\":\"User Frontend\"},{\"v\":\"/setCart\",\"l\":\"Set Cart\",\"m\":\"post\",\"group\":\"User Frontend\"},{\"v\":\"/addItemToCart\",\"l\":\"Add Item to Cart\",\"m\":\"post\",\"group\":\"User Frontend\"},{\"v\":\"/deleteItems\",\"l\":\"Delete Items\",\"m\":\"post\",\"group\":\"User Frontend\"}],\"awareness\":true,\"extKeyRequired\":true}},\"extKeyRequired\":true,\"hosts\":{\"1\":[\"127.0.0.1\"],\"latest\":1},\"requestTimeoutRenewal\":5,\"version\":1,\"oauth\":false,\"requestTimeout\":30,\"group\":\"store\"},\"urac\":{\"port\":4001,\"versions\":{\"1\":{\"apis\":[],\"extKeyRequired\":true},\"2\":{\"urac_Profile\":false,\"apis\":[{\"v\":\"/passport/login/:strategy\",\"l\":\"Login Through Passport\",\"m\":\"get\",\"group\":\"Guest\"},{\"v\":\"/passport/validate/:strategy\",\"l\":\"Login Through Passport Validate\",\"m\":\"get\",\"group\":\"Guest\"},{\"v\":\"/join/validate\",\"l\":\"Validate Register\",\"m\":\"get\",\"group\":\"Guest\"},{\"v\":\"/forgotPassword\",\"l\":\"Forgot Password\",\"m\":\"get\",\"group\":\"Guest\"},{\"v\":\"/checkUsername\",\"l\":\"Check If Username Exists\",\"m\":\"get\",\"group\":\"Guest\"},{\"v\":\"/changeEmail/validate\",\"l\":\"Validate Change Email\",\"m\":\"get\",\"group\":\"Guest\"},{\"groupMain\":true,\"v\":\"/account/getUser\",\"l\":\"Get User Info\",\"m\":\"get\",\"group\":\"My Account\"},{\"v\":\"/admin/changeUserStatus\",\"l\":\"Change User Status\",\"m\":\"get\",\"group\":\"Administration\"},{\"groupMain\":true,\"v\":\"/admin/listUsers\",\"l\":\"List Users\",\"m\":\"get\",\"group\":\"Administration\"},{\"v\":\"/admin/users/count\",\"l\":\"Total Users Count\",\"m\":\"get\",\"group\":\"Administration\"},{\"v\":\"/admin/getUser\",\"l\":\"Get User Record\",\"m\":\"get\",\"group\":\"Administration\"},{\"v\":\"/admin/group/list\",\"l\":\"List Groups\",\"m\":\"get\",\"group\":\"Administration\"},{\"v\":\"/admin/all\",\"l\":\"Get all Users & Groups\",\"m\":\"get\",\"group\":\"Administration\"},{\"v\":\"/owner/admin/users/count\",\"l\":\"Total Users Count\",\"m\":\"get\",\"group\":\"Owner\"},{\"groupMain\":true,\"v\":\"/owner/admin/listUsers\",\"l\":\"List Users\",\"m\":\"get\",\"group\":\"Owner\"},{\"v\":\"/owner/admin/changeUserStatus\",\"l\":\"Change User Status\",\"m\":\"get\",\"group\":\"Owner\"},{\"v\":\"/owner/admin/getUser\",\"l\":\"Get User Record\",\"m\":\"get\",\"group\":\"Owner\"},{\"v\":\"/owner/admin/group/list\",\"l\":\"List Groups\",\"m\":\"get\",\"group\":\"Owner\"},{\"v\":\"/owner/admin/tokens/list\",\"l\":\"List Tokens\",\"m\":\"get\",\"group\":\"Owner\"},{\"v\":\"/openam/login\",\"l\":\"OpenAM Login\",\"m\":\"post\",\"group\":\"Guest\"},{\"v\":\"/ldap/login\",\"l\":\"Ldap Login\",\"m\":\"post\",\"group\":\"Guest\"},{\"v\":\"/join\",\"l\":\"Register\",\"m\":\"post\",\"group\":\"Guest\"},{\"v\":\"/resetPassword\",\"l\":\"Reset Password\",\"m\":\"post\",\"group\":\"Guest\"},{\"v\":\"/account/changePassword\",\"l\":\"Change Password\",\"m\":\"post\",\"group\":\"My Account\"},{\"v\":\"/account/changeEmail\",\"l\":\"Change Email\",\"m\":\"post\",\"group\":\"My Account\"},{\"v\":\"/account/editProfile\",\"l\":\"Edit Profile\",\"m\":\"post\",\"group\":\"My Account\"},{\"v\":\"/admin/addUser\",\"l\":\"Add new User\",\"m\":\"post\",\"group\":\"Administration\"},{\"v\":\"/admin/editUser\",\"l\":\"Edit User Record\",\"m\":\"post\",\"group\":\"Administration\"},{\"v\":\"/admin/editUserConfig\",\"l\":\"Edit User Config\",\"m\":\"post\",\"group\":\"Administration\"},{\"v\":\"/admin/group/add\",\"l\":\"Add new Group\",\"m\":\"post\",\"group\":\"Administration\"},{\"v\":\"/admin/group/edit\",\"l\":\"Edit Group\",\"m\":\"post\",\"group\":\"Administration\"},{\"v\":\"/admin/group/addUsers\",\"l\":\"Add Users to Group\",\"m\":\"post\",\"group\":\"Administration\"},{\"v\":\"/owner/admin/addUser\",\"l\":\"Add new User\",\"m\":\"post\",\"group\":\"Owner\"},{\"v\":\"/owner/admin/editUser\",\"l\":\"Edit User Record\",\"m\":\"post\",\"group\":\"Owner\"},{\"v\":\"/owner/admin/editUserConfig\",\"l\":\"Edit User Config\",\"m\":\"post\",\"group\":\"Owner\"},{\"v\":\"/owner/admin/group/add\",\"l\":\"Add new Group\",\"m\":\"post\",\"group\":\"Owner\"},{\"v\":\"/owner/admin/group/edit\",\"l\":\"Edit Group\",\"m\":\"post\",\"group\":\"Owner\"},{\"v\":\"/owner/admin/group/addUsers\",\"l\":\"Add Users to Group\",\"m\":\"post\",\"group\":\"Owner\"},{\"v\":\"/admin/group/delete\",\"l\":\"Delete Group\",\"m\":\"delete\",\"group\":\"Administration\"},{\"v\":\"/owner/admin/group/delete\",\"l\":\"Delete Group\",\"m\":\"delete\",\"group\":\"Owner\"},{\"v\":\"/owner/admin/tokens/delete\",\"l\":\"Delete Token\",\"m\":\"delete\",\"group\":\"Owner\"}],\"extKeyRequired\":true,\"urac\":false,\"provision_ACL\":false,\"oauth\":true,\"urac_ACL\":false}},\"extKeyRequired\":true,\"hosts\":{\"2\":[\"127.0.0.1\"],\"latest\":2},\"requestTimeoutRenewal\":5,\"version\":1,\"oauth\":true,\"requestTimeout\":30,\"group\":\"SOAJS Core Services\"},\"drivers\":{\"port\":4135,\"versions\":{\"1\":{\"apis\":[{\"groupMain\":true,\"v\":\"/loadDrivers\",\"l\":\"Load Drivers per type\",\"group\":\"Drivers\"},{\"v\":\"/getDriverInfo\",\"l\":\"Load Driver Information\",\"group\":\"Drivers\"},{\"v\":\"/executeDriver\",\"l\":\"Execute driver command\",\"group\":\"Drivers\"}],\"awareness\":true,\"extKeyRequired\":true}},\"extKeyRequired\":true,\"hosts\":{\"1\":[\"127.0.0.1\"],\"latest\":1},\"requestTimeoutRenewal\":5,\"version\":1,\"oauth\":false,\"requestTimeout\":30,\"group\":\"store\"},\"oauth\":{\"port\":4002,\"versions\":{\"1\":{\"urac_Profile\":false,\"apis\":[{\"v\":\"/authorization\",\"l\":\"Get the authorization value\",\"m\":\"get\"},{\"v\":\"/token\",\"l\":\"Create Token\",\"m\":\"post\"},{\"v\":\"/accessToken/:token\",\"l\":\"Delete Access Token\",\"m\":\"delete\"},{\"v\":\"/refreshToken/:token\",\"l\":\"Delete Refresh Token\",\"m\":\"delete\"},{\"v\":\"/tokens/user/:userId\",\"l\":\"Delete all Tokens for this User\",\"m\":\"delete\"},{\"v\":\"/tokens/tenant/:clientId\",\"l\":\"Delete all Tokens for this Client\",\"m\":\"delete\"}],\"extKeyRequired\":true,\"urac\":false,\"provision_ACL\":false,\"oauth\":true,\"urac_ACL\":false}},\"extKeyRequired\":true,\"hosts\":{\"1\":[\"127.0.0.1\"],\"latest\":1},\"requestTimeoutRenewal\":5,\"version\":1,\"oauth\":true,\"requestTimeout\":30,\"group\":\"SOAJS Core Services\"},\"dashboard\":{\"port\":4003,\"versions\":{\"1\":{\"urac_Profile\":true,\"apis\":[{\"v\":\"/cd/ledger\",\"l\":\"Lists the ledgers of a specific environment\",\"m\":\"get\",\"group\":\"Continuous Delivery\"},{\"v\":\"/environment\",\"l\":\"Get Environment\",\"m\":\"get\",\"group\":\"Environment\"},{\"groupMain\":true,\"v\":\"/environment/list\",\"l\":\"List Environments\",\"m\":\"get\",\"group\":\"Environment\"},{\"v\":\"/environment/dbs/list\",\"l\":\"List Environment Databases\",\"m\":\"get\",\"group\":\"Environment Databases\"},{\"v\":\"/environment/clusters/list\",\"l\":\"List Environment Database Clusters\",\"m\":\"get\",\"group\":\"Environment Clusters\"},{\"v\":\"/environment/platforms/list\",\"l\":\"List Environment Platforms\",\"m\":\"get\",\"group\":\"Environment Platforms\"},{\"groupMain\":true,\"v\":\"/product/list\",\"l\":\"List Products\",\"m\":\"get\",\"group\":\"Product\"},{\"v\":\"/product/get\",\"l\":\"Get Product\",\"m\":\"get\",\"group\":\"Product\"},{\"v\":\"/product/packages/list\",\"l\":\"List Product Packages\",\"m\":\"get\",\"group\":\"Product\"},{\"v\":\"/product/packages/get\",\"l\":\"Get Product Package\",\"m\":\"get\",\"group\":\"Product\"},{\"v\":\"/permissions/get\",\"l\":\"Get Tenant Security Permissions\",\"m\":\"get\",\"group\":\"Tenant\"},{\"v\":\"/key/get\",\"l\":\"Get the user dashboard key\",\"m\":\"get\",\"group\":\"Tenant\"},{\"v\":\"/tenant/list\",\"l\":\"List Tenants\",\"m\":\"get\",\"group\":\"Tenant\"},{\"v\":\"/tenant/get\",\"l\":\"Get Tenant\",\"m\":\"get\",\"group\":\"Tenant\"},{\"v\":\"/tenant/oauth/list\",\"l\":\"Get Tenant oAuth Configuration\",\"m\":\"get\",\"group\":\"Tenant oAuth\"},{\"v\":\"/tenant/oauth/users/list\",\"l\":\"List Tenant oAuth Users\",\"m\":\"get\",\"group\":\"Tenant oAuth\"},{\"v\":\"/tenant/application/list\",\"l\":\"List Tenant Applications\",\"m\":\"get\",\"group\":\"Tenant Application\"},{\"v\":\"/tenant/application/key/list\",\"l\":\"List Tenant Application Keys\",\"m\":\"get\",\"group\":\"Tenant Application\"},{\"v\":\"/tenant/application/key/ext/list\",\"l\":\"List Tenant Application External Keys\",\"m\":\"get\",\"group\":\"Tenant Application\"},{\"v\":\"/tenant/application/key/config/list\",\"l\":\"List Tenant Application Key Configuration\",\"m\":\"get\",\"group\":\"Tenant Application\"},{\"groupMain\":true,\"v\":\"/tenant/db/keys/list\",\"l\":\"List Dashboard Tenant Keys\",\"m\":\"get\",\"group\":\"Dashboard Tenants\"},{\"v\":\"/settings/tenant/get\",\"l\":\"Get Tenant\",\"m\":\"get\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/oauth/list\",\"l\":\"Get Tenant oAuth Configuration\",\"m\":\"get\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/oauth/users/list\",\"l\":\"List Tenant oAuth Users\",\"m\":\"get\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/application/list\",\"l\":\"List Tenant Applications\",\"m\":\"get\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/application/key/list\",\"l\":\"List Tenant Application Keys\",\"m\":\"get\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/application/key/ext/list\",\"l\":\"List Tenant Application External Keys\",\"m\":\"get\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/application/key/config/list\",\"l\":\"List Tenant Application Key Configuration\",\"m\":\"get\",\"group\":\"Tenant Settings\"},{\"v\":\"/services/env/list\",\"l\":\"List The Environment Where A Service Is Deployed\",\"m\":\"get\",\"group\":\"Services\"},{\"v\":\"/daemons/groupConfig/serviceConfig/list\",\"l\":\"List Service Configuration\",\"m\":\"get\",\"group\":\"Daemons\"},{\"v\":\"/daemons/groupConfig/tenantExtKeys/list\",\"l\":\"List Job's External Keys\",\"m\":\"get\",\"group\":\"Daemons\"},{\"groupMain\":true,\"v\":\"/hosts/list\",\"l\":\"List Hosts\",\"m\":\"get\",\"group\":\"Hosts\"},{\"v\":\"/cloud/services/list\",\"l\":\"List Cloud Services\",\"m\":\"get\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/nodes/list\",\"l\":\"List HA Cloud Nodes\",\"m\":\"get\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/services/instances/logs\",\"l\":\"Get Service Container Logs\",\"m\":\"get\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/namespaces/list\",\"l\":\"List Available Namespaces\",\"m\":\"get\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/heapster\",\"l\":\"Check if Heapster is Deployed\",\"m\":\"get\",\"group\":\"HA Cloud\"},{\"v\":\"/catalog/recipes/list\",\"l\":\"List Catalog Recipes\",\"m\":\"get\",\"group\":\"Catalog\"},{\"v\":\"/catalog/recipes/get\",\"l\":\"Get a Catalog\",\"m\":\"get\",\"group\":\"Catalog\"},{\"v\":\"/catalog/recipes/upgrade\",\"l\":\"Upgrade Catalog Recipes to latest Version\",\"m\":\"get\",\"group\":\"Catalog\"},{\"v\":\"/cd\",\"l\":\"Get CD Configuration\",\"m\":\"get\",\"group\":\"Continuous Delivery\"},{\"v\":\"/cd/updates\",\"l\":\"Get Update Notification Ledger\",\"m\":\"get\",\"group\":\"Continuous Delivery\"},{\"v\":\"/ci\",\"l\":\"Get CI Accounts\",\"m\":\"get\",\"group\":\"Continuous Integration\"},{\"v\":\"/ci/providers\",\"l\":\"Get CI Providers\",\"m\":\"get\",\"group\":\"Continuous Integration\"},{\"v\":\"/ci/recipe/download\",\"l\":\"Download CI Recipe\",\"m\":\"get\",\"group\":\"Continuous Integration\"},{\"v\":\"/ci/script/download\",\"l\":\"Download CI Script\",\"m\":\"get\",\"group\":\"Continuous Integration\"},{\"v\":\"/ci/status\",\"l\":\"Turn On/Off Repository CI\",\"m\":\"get\",\"group\":\"Continuous Integration\"},{\"v\":\"/ci/settings\",\"l\":\"Get CI Repository Settings & Environment Variables\",\"m\":\"get\",\"group\":\"Continuous Integration\"},{\"v\":\"/ci/repo/remote/config\",\"l\":\"Get the CI configuration file of the repository from provider\",\"m\":\"get\",\"group\":\"Continuous Integration\"},{\"v\":\"/gitAccounts/accounts/list\",\"l\":\"List Git Accounts\",\"m\":\"get\",\"group\":\"Git Accounts\"},{\"v\":\"/gitAccounts/getRepos\",\"l\":\"Get Repositories\",\"m\":\"get\",\"group\":\"Git Accounts\"},{\"v\":\"/gitAccounts/getBranches\",\"l\":\"Get Repository Branches\",\"m\":\"get\",\"group\":\"Git Accounts\"},{\"groupMain\":true,\"v\":\"/cb/list\",\"l\":\"List Content Schema\",\"m\":\"get\",\"group\":\"Content Builder\"},{\"v\":\"/cb/get\",\"l\":\"Get One Content Schema\",\"m\":\"get\",\"group\":\"Content Builder\"},{\"v\":\"/cb/listRevisions\",\"l\":\"List Content Schema Revisions\",\"m\":\"get\",\"group\":\"Content Builder\"},{\"v\":\"/gitAccounts/getYaml\",\"l\":\"Get Yaml file\",\"m\":\"get\",\"group\":\"Git Accounts\"},{\"v\":\"/analytics/getSettings\",\"l\":\"Get Analytics Settings\",\"m\":\"get\",\"group\":\"elk\"},{\"v\":\"/analytics/activateAnalytics\",\"l\":\"Activate Analytics\",\"m\":\"get\",\"group\":\"elk\"},{\"v\":\"/analytics/deactivateAnalytics\",\"l\":\"Deactivate Analytics\",\"m\":\"get\",\"group\":\"elk\"},{\"v\":\"/services/list\",\"l\":\"List Services\",\"m\":\"post\",\"group\":\"Services\"},{\"v\":\"/environment/add\",\"l\":\"Add Environment\",\"m\":\"post\",\"group\":\"Environment\"},{\"v\":\"/environment/dbs/add\",\"l\":\"Add Environment Database\",\"m\":\"post\",\"group\":\"Environment Databases\"},{\"v\":\"/environment/clusters/add\",\"l\":\"Add Environment Database Cluster\",\"m\":\"post\",\"group\":\"Environment Clusters\"},{\"v\":\"/environment/platforms/cert/upload\",\"l\":\"Upload Certificate\",\"m\":\"post\",\"group\":\"Environment Platforms\"},{\"v\":\"/product/add\",\"l\":\"Add Product\",\"m\":\"post\",\"group\":\"Product\"},{\"v\":\"/product/packages/add\",\"l\":\"Add Product Package\",\"m\":\"post\",\"group\":\"Product\"},{\"v\":\"/tenant/add\",\"l\":\"Add Tenant\",\"m\":\"post\",\"group\":\"Tenant\"},{\"v\":\"/tenant/oauth/add\",\"l\":\"Add Tenant oAuth Configuration\",\"m\":\"post\",\"group\":\"Tenant oAuth\"},{\"v\":\"/tenant/oauth/users/add\",\"l\":\"Add Tenant oAuth User\",\"m\":\"post\",\"group\":\"Tenant oAuth\"},{\"v\":\"/tenant/application/add\",\"l\":\"Add Tenant Application\",\"m\":\"post\",\"group\":\"Tenant Application\"},{\"v\":\"/tenant/application/key/add\",\"l\":\"Add Tenant Application Key\",\"m\":\"post\",\"group\":\"Tenant Application\"},{\"v\":\"/tenant/application/key/ext/add\",\"l\":\"Add Tenant Application External Key\",\"m\":\"post\",\"group\":\"Tenant Application\"},{\"v\":\"/tenant/application/key/ext/delete\",\"l\":\"Delete Tenant Application External Key\",\"m\":\"post\",\"group\":\"Tenant Application\"},{\"v\":\"/tenant/acl/get\",\"l\":\"Get Current Tenant Access Level\",\"m\":\"post\",\"group\":\"Tenant\"},{\"v\":\"/settings/tenant/oauth/add\",\"l\":\"Add Tenant oAuth Configuration\",\"m\":\"post\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/oauth/users/add\",\"l\":\"Add Tenant oAuth User\",\"m\":\"post\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/application/key/add\",\"l\":\"Add Tenant Application Key\",\"m\":\"post\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/application/key/ext/add\",\"l\":\"Add Tenant Application External Key\",\"m\":\"post\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/application/key/ext/delete\",\"l\":\"Delete Tenant Application External Key\",\"m\":\"post\",\"group\":\"Tenant Settings\"},{\"v\":\"/daemons/groupConfig/list\",\"l\":\"List Daemon Group Configuration\",\"m\":\"post\",\"group\":\"Daemons\"},{\"v\":\"/daemons/groupConfig/add\",\"l\":\"Add Daemon Group Configuration\",\"m\":\"post\",\"group\":\"Daemons\"},{\"v\":\"/daemons/list\",\"l\":\"List Daemons\",\"m\":\"post\",\"group\":\"Daemons\"},{\"v\":\"/cloud/services/soajs/deploy\",\"l\":\"Deploy A New SOAJS Service\",\"m\":\"post\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/plugins/deploy\",\"l\":\"Deploy A Custom Resource\",\"m\":\"post\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/nodes/add\",\"l\":\"Add HA Cloud Node\",\"m\":\"post\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/services/maintenance\",\"l\":\"Perform A Maintenance Operation on a Deployed Service\",\"m\":\"post\",\"group\":\"HA Cloud\"},{\"v\":\"/catalog/recipes/add\",\"l\":\"Add New Catalog\",\"m\":\"post\",\"group\":\"Catalog\"},{\"v\":\"/ci/provider\",\"l\":\"Activate CI Provider\",\"m\":\"post\",\"group\":\"Continuous Integration\"},{\"v\":\"/ci/recipe\",\"l\":\"Add New CI Recipe\",\"m\":\"post\",\"group\":\"Continuous Integration\"},{\"v\":\"/cd\",\"l\":\"Save CD Configuration for a specific Service\",\"m\":\"post\",\"group\":\"Continuous Delivery\"},{\"v\":\"/cd/pause\",\"l\":\"Pause CD Configuration\",\"m\":\"post\",\"group\":\"Continuous Delivery\"},{\"v\":\"/cd/deploy\",\"l\":\"Trigger CD Deployment\",\"m\":\"post\",\"group\":\"Continuous Delivery\"},{\"v\":\"/gitAccounts/login\",\"l\":\"Github Login\",\"m\":\"post\",\"group\":\"Git Accounts\"},{\"v\":\"/gitAccounts/repo/activate\",\"l\":\"Activate Repository\",\"m\":\"post\",\"group\":\"Git Accounts\"},{\"v\":\"/cb/add\",\"l\":\"Add New Content Schema\",\"m\":\"post\",\"group\":\"Content Builder\"},{\"v\":\"/hosts/maintenanceOperation\",\"l\":\"Perform Maintenance Operation\",\"m\":\"post\",\"group\":\"Hosts\"},{\"groupMain\":true,\"v\":\"/swagger/simulate\",\"l\":\"Api simulation service\",\"m\":\"post\",\"group\":\"Simulate\"},{\"groupMain\":true,\"v\":\"/swagger/generate\",\"l\":\"Generate Service via Swagger\",\"m\":\"post\",\"group\":\"swagger\"},{\"v\":\"/services/settings/update\",\"l\":\"Updates Service Settings\",\"m\":\"put\",\"group\":\"Services\"},{\"v\":\"/cd/ledger/read\",\"l\":\"Mark as read\",\"m\":\"put\",\"group\":\"Continuous Delivery\"},{\"v\":\"/cd/action\",\"l\":\"Take Action\",\"m\":\"put\",\"group\":\"Continuous Delivery\"},{\"v\":\"/environment/update\",\"l\":\"Update Environment\",\"m\":\"put\",\"group\":\"Environment\"},{\"v\":\"/environment/key/update\",\"l\":\"Update Environment Tenant Key Security\",\"m\":\"put\",\"group\":\"Environment\"},{\"v\":\"/environment/dbs/update\",\"l\":\"Update Environment Database\",\"m\":\"put\",\"group\":\"Environment Databases\"},{\"v\":\"/environment/dbs/updatePrefix\",\"l\":\"Update Environment Databases Prefix\",\"m\":\"put\",\"group\":\"Environment Databases\"},{\"v\":\"/environment/clusters/update\",\"l\":\"Update Environment Database Cluster\",\"m\":\"put\",\"group\":\"Environment Clusters\"},{\"v\":\"/environment/platforms/cert/choose\",\"l\":\"Choose Existing Certificates\",\"m\":\"put\",\"group\":\"Environment Platforms\"},{\"v\":\"/environment/platforms/driver/changeSelected\",\"l\":\"Change Selected Driver\",\"m\":\"put\",\"group\":\"Environment Platforms\"},{\"v\":\"/environment/platforms/deployer/type/change\",\"l\":\"Change Deployer Type\",\"m\":\"put\",\"group\":\"Environment Platforms\"},{\"v\":\"/environment/platforms/deployer/update\",\"l\":\"Change Deployer Type\",\"m\":\"put\",\"group\":\"Environment Platforms\"},{\"v\":\"/product/update\",\"l\":\"Update Product\",\"m\":\"put\",\"group\":\"Product\"},{\"v\":\"/product/packages/update\",\"l\":\"Update Product Package\",\"m\":\"put\",\"group\":\"Product\"},{\"v\":\"/tenant/update\",\"l\":\"Update Tenant\",\"m\":\"put\",\"group\":\"Tenant\"},{\"v\":\"/tenant/oauth/update\",\"l\":\"Update Tenant oAuth Configuration\",\"m\":\"put\",\"group\":\"Tenant oAuth\"},{\"v\":\"/tenant/oauth/users/update\",\"l\":\"Update Tenant oAuth User\",\"m\":\"put\",\"group\":\"Tenant oAuth\"},{\"v\":\"/tenant/application/update\",\"l\":\"Update Tenant Application\",\"m\":\"put\",\"group\":\"Tenant Application\"},{\"v\":\"/tenant/application/key/ext/update\",\"l\":\"Update Tenant Application External Key\",\"m\":\"put\",\"group\":\"Tenant Application\"},{\"v\":\"/tenant/application/key/config/update\",\"l\":\"Update Tenant Application Key Configuration\",\"m\":\"put\",\"group\":\"Tenant Application\"},{\"v\":\"/settings/tenant/update\",\"l\":\"Update Tenant\",\"m\":\"put\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/oauth/update\",\"l\":\"Update Tenant oAuth Configuration\",\"m\":\"put\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/oauth/users/update\",\"l\":\"Update Tenant oAuth User\",\"m\":\"put\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/application/key/ext/update\",\"l\":\"Update Tenant Application External Key\",\"m\":\"put\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/application/key/config/update\",\"l\":\"Update Tenant Application Key Configuration\",\"m\":\"put\",\"group\":\"Tenant Settings\"},{\"v\":\"/daemons/groupConfig/update\",\"l\":\"Update Daemon Group Configuration\",\"m\":\"put\",\"group\":\"Daemons\"},{\"v\":\"/daemons/groupConfig/serviceConfig/update\",\"l\":\"Update Service Configuration\",\"m\":\"put\",\"group\":\"Daemons\"},{\"v\":\"/daemons/groupConfig/tenantExtKeys/update\",\"l\":\"Update Job's External Keys\",\"m\":\"put\",\"group\":\"Daemons\"},{\"v\":\"/cloud/nodes/update\",\"l\":\"Update HA Cloud Node\",\"m\":\"put\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/nodes/tag\",\"l\":\"Update HA Cloud Node Tag\",\"m\":\"put\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/services/scale\",\"l\":\"Scale HA Service\",\"m\":\"put\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/services/redeploy\",\"l\":\"Redeploy HA Service\",\"m\":\"put\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/services/autoscale\",\"l\":\"Autoscale Services\",\"m\":\"put\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/services/autoscale/config\",\"l\":\"Configure Environment Autoscaling\",\"m\":\"put\",\"group\":\"HA Cloud\"},{\"v\":\"/catalog/recipes/update\",\"l\":\"Update Catalog\",\"m\":\"put\",\"group\":\"Catalog\"},{\"v\":\"/gitAccounts/repo/sync\",\"l\":\"Deactivate Repository\",\"m\":\"put\",\"group\":\"Git Accounts\"},{\"v\":\"/ci/provider\",\"l\":\"Deactivate CI Provider\",\"m\":\"put\",\"group\":\"Continuous Integration\"},{\"v\":\"/ci/recipe\",\"l\":\"Edit CI Recipe\",\"m\":\"put\",\"group\":\"Continuous Integration\"},{\"v\":\"/ci/settings\",\"l\":\"Update CI Repository Settings\",\"m\":\"put\",\"group\":\"Continuous Integration\"},{\"v\":\"/cb/update\",\"l\":\"Update Content Schema\",\"m\":\"put\",\"group\":\"Content Builder\"},{\"v\":\"/gitAccounts/repo/deactivate\",\"l\":\"Deactivate Repository\",\"m\":\"put\",\"group\":\"Git Accounts\"},{\"v\":\"/environment/delete\",\"l\":\"Delete Environment\",\"m\":\"delete\",\"group\":\"Environment\"},{\"v\":\"/environment/dbs/delete\",\"l\":\"Delete Environment Database\",\"m\":\"delete\",\"group\":\"Environment Databases\"},{\"v\":\"/environment/clusters/delete\",\"l\":\"Delete Environment Database Cluster\",\"m\":\"delete\",\"group\":\"Environment Clusters\"},{\"v\":\"/environment/platforms/cert/delete\",\"l\":\"Remove Certificate\",\"m\":\"delete\",\"group\":\"Environment Platforms\"},{\"v\":\"/product/delete\",\"l\":\"Delete Product\",\"m\":\"delete\",\"group\":\"Product\"},{\"v\":\"/product/packages/delete\",\"l\":\"Delete Product Package\",\"m\":\"delete\",\"group\":\"Product\"},{\"v\":\"/tenant/delete\",\"l\":\"Delete Tenant\",\"m\":\"delete\",\"group\":\"Tenant\"},{\"v\":\"/tenant/oauth/delete\",\"l\":\"Delete Tenant oAuth Configuration\",\"m\":\"delete\",\"group\":\"Tenant oAuth\"},{\"v\":\"/tenant/oauth/users/delete\",\"l\":\"Delete Tenant oAuth User\",\"m\":\"delete\",\"group\":\"Tenant oAuth\"},{\"v\":\"/tenant/application/delete\",\"l\":\"Delete Tenant Application\",\"m\":\"delete\",\"group\":\"Tenant Application\"},{\"v\":\"/tenant/application/key/delete\",\"l\":\"Delete Tenant Application Key\",\"m\":\"delete\",\"group\":\"Tenant Application\"},{\"v\":\"/settings/tenant/oauth/delete\",\"l\":\"Delete Tenant oAuth Configuration\",\"m\":\"delete\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/oauth/users/delete\",\"l\":\"Delete Tenant oAuth User\",\"m\":\"delete\",\"group\":\"Tenant Settings\"},{\"v\":\"/settings/tenant/application/key/delete\",\"l\":\"Delete Tenant Application Key\",\"m\":\"delete\",\"group\":\"Tenant Settings\"},{\"v\":\"/daemons/groupConfig/delete\",\"l\":\"Delete Daemon Group Configuration\",\"m\":\"delete\",\"group\":\"Daemons\"},{\"v\":\"/cloud/nodes/remove\",\"l\":\"Remove HA Cloud Node\",\"m\":\"delete\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/services/delete\",\"l\":\"Delete HA Service\",\"m\":\"delete\",\"group\":\"HA Cloud\"},{\"v\":\"/cloud/namespaces/delete\",\"l\":\"Delete a Namespace\",\"m\":\"delete\",\"group\":\"HA Cloud\"},{\"v\":\"/catalog/recipes/delete\",\"l\":\"Delete a Catalog\",\"m\":\"delete\",\"group\":\"Catalog\"},{\"v\":\"/ci/recipe\",\"l\":\"Delete CI Recipe\",\"m\":\"delete\",\"group\":\"Continuous Integration\"},{\"v\":\"/gitAccounts/logout\",\"l\":\"Github Logout\",\"m\":\"delete\",\"group\":\"Git Accounts\"}],\"extKeyRequired\":true,\"urac\":true,\"provision_ACL\":true,\"oauth\":true,\"urac_ACL\":true}},\"extKeyRequired\":true,\"hosts\":{\"1\":[\"127.0.0.1\"],\"latest\":1},\"requestTimeoutRenewal\":5,\"version\":1,\"oauth\":true,\"requestTimeout\":60,\"group\":\"SOAJS Core Services\"},\"kbprofile\":{\"port\":4137,\"versions\":{\"1\":{\"urac_Profile\":false,\"apis\":[{\"groupMain\":true,\"v\":\"/owner/feed/profiles/list\",\"l\":\"List Feeder Profiles\",\"m\":\"get\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/configuration\",\"l\":\"List Feeder Profile General Configuration\",\"m\":\"get\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/treats\",\"l\":\"List Feeder Profile Treats\",\"m\":\"get\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/filters\",\"l\":\"List Feeder Profile Filters\",\"m\":\"get\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/attributes\",\"l\":\"List Feeder Profile Attributes\",\"m\":\"get\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/tokens\",\"l\":\"List Feeder Profile Tokens\",\"m\":\"get\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/categories\",\"l\":\"List Feeder Profile Categories\",\"m\":\"get\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id\",\"l\":\"Get Feeder Profile\",\"m\":\"get\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/active/profiles\",\"l\":\"Get Active Feeder Profile\",\"m\":\"get\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id\",\"l\":\"Update Feeder Profile\",\"m\":\"put\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/configuration\",\"l\":\"Update Configuration\",\"m\":\"put\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/treat\",\"l\":\"Update Treat\",\"m\":\"put\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/classification\",\"l\":\"Update Classification\",\"m\":\"put\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/department\",\"l\":\"Update Department\",\"m\":\"put\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/token\",\"l\":\"Update Token\",\"m\":\"put\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/filter\",\"l\":\"Update Filter\",\"m\":\"put\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/category\",\"l\":\"Update Category\",\"m\":\"put\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/treat\",\"l\":\"Delete Treat\",\"m\":\"delete\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/filter\",\"l\":\"Delete Filter\",\"m\":\"delete\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/classification\",\"l\":\"Delete Classification\",\"m\":\"delete\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/department\",\"l\":\"Delete Department\",\"m\":\"delete\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/token\",\"l\":\"Delete Token\",\"m\":\"delete\",\"group\":\"Feed Profile Configuration\"},{\"v\":\"/owner/feed/profiles/:id/category\",\"l\":\"Delete Category\",\"m\":\"delete\",\"group\":\"Feed Profile Configuration\"}],\"extKeyRequired\":true,\"urac\":false,\"provision_ACL\":false,\"oauth\":true,\"urac_ACL\":false}},\"extKeyRequired\":true,\"hosts\":{\"1\":[\"127.0.0.1\"],\"latest\":1},\"requestTimeoutRenewal\":5,\"version\":1,\"oauth\":true,\"requestTimeout\":60,\"group\":\"store\"},\"tomatina\":{\"port\":4091,\"versions\":{\"1\":{\"urac_Profile\":false,\"apis\":[{\"v\":\"/getPosts\",\"l\":\"Get Wall posts\",\"m\":\"get\",\"group\":\"Projects\"},{\"v\":\"/throwTomato\",\"l\":\"Throw Tomato\",\"m\":\"get\",\"group\":\"Projects\"},{\"v\":\"/login\",\"l\":\"Register and login user\",\"m\":\"get\",\"group\":\"Projects\"},{\"v\":\"/submitLocation\",\"l\":\"Submit Location\",\"m\":\"post\",\"group\":\"Projects\"},{\"v\":\"/addUpdatePost\",\"l\":\"Add Or Update Post\",\"m\":\"post\",\"group\":\"Projects\"},{\"v\":\"/deleteSubmission\",\"l\":\"Delete submission\",\"m\":\"delete\",\"group\":\"Projects\"}],\"extKeyRequired\":false,\"urac\":false,\"provision_ACL\":false,\"oauth\":false,\"urac_ACL\":false}},\"extKeyRequired\":false,\"hosts\":{\"1\":[\"127.0.0.1\"],\"latest\":1},\"requestTimeoutRenewal\":5,\"version\":1,\"oauth\":false,\"requestTimeout\":30,\"group\":\"ETD\"},\"order\":{\"port\":4130,\"versions\":{\"1\":{\"apis\":[{\"v\":\"/createUserInfo\",\"l\":\"Create New UserInfo Record\",\"m\":\"get\",\"group\":\"User Orders\"},{\"v\":\"/getUserInfo\",\"l\":\"Get UserInfo & Merchants Configuration\",\"m\":\"get\",\"group\":\"User Orders\"},{\"groupMain\":true,\"v\":\"/owner/shippingMethods/list\",\"l\":\"List Shipping Methods\",\"m\":\"get\",\"group\":\"Owner Dashboard Shipping Methods\"},{\"v\":\"/owner/shippingMethods/delete\",\"l\":\"Delete Shipping Method\",\"m\":\"get\",\"group\":\"Owner Dashboard Shipping Methods\"},{\"v\":\"/addShippingAddress\",\"l\":\"Add Shipping Address\",\"m\":\"post\",\"group\":\"User Orders\"},{\"v\":\"/getPointsOfSale\",\"l\":\"Get Points of Sale\",\"m\":\"post\",\"group\":\"User Orders\"},{\"v\":\"/getShippingAndDeliveryMethods\",\"l\":\"Get Shipping And Delivery Methods\",\"m\":\"post\",\"group\":\"User Orders\"},{\"v\":\"/getTaxForPickupAddress\",\"l\":\"Get Tax Info for Pickup\",\"m\":\"post\",\"group\":\"User Orders\"},{\"v\":\"/getTaxValues\",\"l\":\"Get Tax Value\",\"m\":\"post\",\"group\":\"User Orders\"},{\"v\":\"/owner/shippingMethods/add\",\"l\":\"Add Shipping Method\",\"m\":\"post\",\"group\":\"Owner Dashboard Shipping Methods\"},{\"v\":\"/owner/deliveryMethods/add\",\"l\":\"Add Delivery Method\",\"m\":\"post\",\"group\":\"Owner Dashboard Delivery Methods\"},{\"v\":\"/owner/shippingMethods/edit\",\"l\":\"Edit Shipping Method\",\"m\":\"post\",\"group\":\"Owner Dashboard Shipping Methods\"},{\"v\":\"/address/:addressKey\",\"l\":\"Delete Shipping Address\",\"m\":\"delete\",\"group\":\"User Orders\"}],\"awareness\":true,\"extKeyRequired\":true}},\"extKeyRequired\":true,\"hosts\":{\"1\":[\"127.0.0.1\"],\"latest\":1},\"requestTimeoutRenewal\":5,\"version\":1,\"oauth\":false,\"requestTimeout\":60,\"group\":\"store\"}},\"coreDB\":{\"esClient\":{\"cluster\":\"esCluster\",\"streaming\":null,\"servers\":[{\"port\":\"9200\",\"host\":\"127.0.0.1\"}],\"credentials\":{},\"prefix\":\"STOR_\",\"URLParam\":{\"protocol\":\"http\"},\"registryLocation\":{\"l1\":\"coreDB\",\"l2\":\"esClient\",\"env\":\"dashboard\"},\"name\":\"esClient\",\"extraParam\":{\"keepAlive\":false,\"maxSockets\":30,\"requestTimeout\":3000}},\"knowledgebase\":{\"cluster\":\"cluster1\",\"streaming\":null,\"servers\":[{\"port\":27017,\"host\":\"127.0.0.1\"}],\"credentials\":null,\"prefix\":\"STOR_\",\"URLParam\":{\"wtimeoutMS\":0,\"slaveOk\":true,\"socketTimeoutMS\":0,\"connectTimeoutMS\":0,\"maxPoolSize\":5},\"registryLocation\":{\"l1\":\"coreDB\",\"l2\":\"knowledgebase\",\"env\":\"dashboard\"},\"name\":\"knowledgebase\",\"extraParam\":{\"server\":{\"socketOptions\":{\"autoReconnect\":false}},\"db\":{\"bufferMaxEntries\":0,\"native_parser\":true}}},\"provision\":{\"streaming\":{\"colName\":{\"batchSize\":10000},\"batchSize\":10000},\"servers\":[{\"port\":27017,\"host\":\"127.0.0.1\"}],\"credentials\":null,\"prefix\":\"STOR_\",\"URLParam\":{\"bufferMaxEntries\":0},\"registryLocation\":{\"l1\":\"coreDB\",\"l2\":\"provision\",\"env\":\"dashboard\"},\"name\":\"core_provision\",\"timeConnected\":1507205730797},\"catalog\":{\"cluster\":\"cluster1\",\"streaming\":null,\"servers\":[{\"port\":27017,\"host\":\"127.0.0.1\"}],\"credentials\":null,\"prefix\":\"STOR_\",\"URLParam\":{\"wtimeoutMS\":0,\"slaveOk\":true,\"socketTimeoutMS\":0,\"connectTimeoutMS\":0,\"maxPoolSize\":5},\"registryLocation\":{\"l1\":\"coreDB\",\"l2\":\"catalog\",\"env\":\"dashboard\"},\"name\":\"catalog\",\"extraParam\":{\"server\":{\"socketOptions\":{\"autoReconnect\":false}},\"db\":{\"bufferMaxEntries\":0,\"native_parser\":true}}},\"session\":{\"cluster\":\"cluster1\",\"servers\":[{\"port\":27017,\"host\":\"127.0.0.1\"}],\"credentials\":null,\"prefix\":\"STOR_\",\"registryLocation\":{\"l1\":\"coreDB\",\"l2\":\"session\",\"env\":\"dashboard\"},\"URLParam\":{\"wtimeoutMS\":0,\"slaveOk\":true,\"socketTimeoutMS\":0,\"connectTimeoutMS\":0,\"maxPoolSize\":5},\"name\":\"core_session\",\"expireAfter\":1209600000,\"extraParam\":{\"server\":{\"socketOptions\":{\"autoReconnect\":false}},\"db\":{\"bufferMaxEntries\":0,\"native_parser\":true}},\"store\":{},\"collection\":\"sessions\",\"stringify\":false},\"commerce\":{\"cluster\":\"cluster1\",\"streaming\":null,\"servers\":[{\"port\":27017,\"host\":\"127.0.0.1\"}],\"credentials\":null,\"prefix\":\"STOR_\",\"URLParam\":{\"wtimeoutMS\":0,\"slaveOk\":true,\"socketTimeoutMS\":0,\"connectTimeoutMS\":0,\"maxPoolSize\":5},\"registryLocation\":{\"l1\":\"coreDB\",\"l2\":\"commerce\",\"env\":\"dashboard\"},\"name\":\"commerce\",\"extraParam\":{\"server\":{\"socketOptions\":{\"autoReconnect\":false}},\"db\":{\"bufferMaxEntries\":0,\"native_parser\":true}}},\"tomatina\":{\"cluster\":\"cluster1\",\"streaming\":null,\"servers\":[{\"port\":27017,\"host\":\"127.0.0.1\"}],\"credentials\":null,\"prefix\":\"STOR_\",\"URLParam\":{\"wtimeoutMS\":0,\"slaveOk\":true,\"socketTimeoutMS\":0,\"connectTimeoutMS\":0,\"maxPoolSize\":5},\"registryLocation\":{\"l1\":\"coreDB\",\"l2\":\"tomatina\",\"env\":\"dashboard\"},\"name\":\"tomatina\",\"extraParam\":{\"server\":{\"socketOptions\":{\"autoReconnect\":false}},\"db\":{\"bufferMaxEntries\":0,\"native_parser\":true}}}}}}");
    
    public SoajsRegistryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        controllerThread = new Thread(new SoajsFakeController());
        controllerThread.start();
        
//        try{
//        URL u = Mockito.mock(URL.class);
//        String url = "http://localhost:5000";
//        
////        Mockito.whenNew(URL.class).withArguments(url).thenReturn(u);
////        Mockito.when(URL.class).thenReturn(u);
//        
//        HttpURLConnection huc = Mockito.mock(HttpURLConnection.class);
//        
//        PowerMocikto.when(u.openConnection()).thenReturn(huc);
//        PowerMocikto.when(huc.getResponseCode()).thenReturn(200);
//        }catch(Exception e){
//            System.out.println("errrrr");
//            e.printStackTrace();
//        }
           
        SoajsRegistry.env = "dashboard";
        SoajsRegistry.serviceName = "test";
        SoajsRegistry.setBy = "tester";
        SoajsRegistry.soajsRegistryApi = "127.0.0.1:5000";
        
        SoajsRegistry.registryStruct = data;
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDatabases method, of class SoajsRegistry.
     */
    @Test
    public void testGetDatabases_0args() {
        System.out.println("getDatabases");
        
        SoajsRegistry.env = "dashboard";
        boolean expResult = true;
        boolean result = SoajsRegistry.getDatabases() != null;
        assertEquals(expResult, result);
        
        SoajsRegistry.env = null;
        boolean expResult2 = false;
        boolean result2 = SoajsRegistry.getDatabases() != null;
        assertEquals(expResult2, result2);
        
        SoajsRegistry.env = "dashboard";
        data.getJSONObject("dashboard").remove("coreDB");
        data.getJSONObject("dashboard").remove("tenantMetaDB");
        
        SoajsRegistry.registryStruct = data;
        boolean expResult3 = true;
        boolean result3 = SoajsRegistry.getDatabases() == null;
        assertEquals(expResult3, result3);
    }

    /**
     * Test of getDatabases method, of class SoajsRegistry.
     */
    @Test
    public void testGetDatabases_String() {
        System.out.println("getDatabases");
        
        SoajsRegistry.env = "dashboard";
        
        
        String dbName = "urac";
        boolean expResult = true;
        boolean result = SoajsRegistry.getDatabases(dbName) != null;
        assertEquals(expResult, result);
        
        String dbName2 = "session";
        boolean expResult2 = true;
        boolean result2 = SoajsRegistry.getDatabases(dbName2) != null;
        assertEquals(expResult2, result2);
        
        String dbName3 = "xxx";
        boolean expResult3 = false;
        boolean result3 = SoajsRegistry.getDatabases(dbName3) != null;
        assertEquals(expResult3, result3);
    }

    /**
     * Test of getServiceConfig method, of class SoajsRegistry.
     */
    @Test
    public void testGetServiceConfig() {
        System.out.println("getServiceConfig");
        
        SoajsRegistry.env = "dashboard";
        boolean expResult = false;
        boolean result = SoajsRegistry.getServiceConfig() == null;
        assertEquals(expResult, result);
        
        SoajsRegistry.env = null;
        boolean expResult2 = true;
        boolean result2 = SoajsRegistry.getServiceConfig() == null;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getDeployer method, of class SoajsRegistry.
     */
    @Test
    public void testGetDeployer() {
        System.out.println("getDeployer");
        
        SoajsRegistry.env = "dashboard";
        boolean expResult = false;
        boolean result = SoajsRegistry.getDeployer() == null;
        assertEquals(expResult, result);
        
        SoajsRegistry.env = null;
        boolean expResult2 = true;
        boolean result2 = SoajsRegistry.getDeployer() == null;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getCustom method, of class SoajsRegistry.
     */
    @Test
    public void testGetCustom() {
        System.out.println("getCustom");
        
        SoajsRegistry.env = "dashboard";
        boolean expResult = false;
        boolean result = SoajsRegistry.getCustom()== null;
        assertEquals(expResult, result);
        
        SoajsRegistry.env = null;
        boolean expResult2 = true;
        boolean result2 = SoajsRegistry.getCustom() == null;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getResources method, of class SoajsRegistry.
     */
    @Test
    public void testGetResources_0args() {
        System.out.println("getResources");
        
        SoajsRegistry.env = "dashboard";
        boolean expResult = false;
        boolean result = SoajsRegistry.getResources()== null;
        assertEquals(expResult, result);
        
        SoajsRegistry.env = null;
        boolean expResult2 = true;
        boolean result2 = SoajsRegistry.getResources() == null;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getResources method, of class SoajsRegistry.
     */
    @Test
    public void testGetResources_String() {
        System.out.println("getResources");
        String resourceName = "test";
        
        SoajsRegistry.env = "dashboard";
        boolean expResult = false;
        boolean result = SoajsRegistry.getResources(resourceName)== null;
        assertEquals(expResult, result);
        
        SoajsRegistry.env = null;
        boolean expResult2 = true;
        boolean result2 = SoajsRegistry.getResources(resourceName) == null;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getServices method, of class SoajsRegistry.
     */
    @Test
    public void testGetServices_0args() {
        System.out.println("getServices");
        
        SoajsRegistry.env = "dashboard";
        boolean expResult = false;
        boolean result = SoajsRegistry.getServices()== null;
        assertEquals(expResult, result);
        
        SoajsRegistry.env = null;
        boolean expResult2 = true;
        boolean result2 = SoajsRegistry.getServices() == null;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getServices method, of class SoajsRegistry.
     */
    @Test
    public void testGetServices_String() {
        System.out.println("getServices");
        String serviceName = "tidbit";
        
        SoajsRegistry.env = "dashboard";
        boolean expResult = false;
        boolean result = SoajsRegistry.getServices(serviceName)== null;
        assertEquals(expResult, result);
        
        SoajsRegistry.env = null;
        boolean expResult2 = true;
        boolean result2 = SoajsRegistry.getServices(serviceName) == null;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getDaemons method, of class SoajsRegistry.
     */
    @Test
    public void testGetDaemons_0args() {
        System.out.println("getDaemons");
        
        SoajsRegistry.env = "dashboard";
        boolean expResult = false;
        boolean result = SoajsRegistry.getDaemons()== null;
        assertEquals(expResult, result);
        
        SoajsRegistry.env = null;
        boolean expResult2 = true;
        boolean result2 = SoajsRegistry.getDaemons() == null;
        assertEquals(expResult2, result2);
    }

    /**
     * Test of getDaemons method, of class SoajsRegistry.
     */
    @Test
    public void testGetDaemons_String() {
        System.out.println("getDaemons");
        String daemonName = "cleaner";
        
        SoajsRegistry.env = "dashboard";
        boolean expResult = false;
        boolean result = SoajsRegistry.getDaemons(daemonName)== null;
        assertEquals(expResult, result);
        
        SoajsRegistry.env = null;
        boolean expResult2 = true;
        boolean result2 = SoajsRegistry.getDaemons(daemonName) == null;
        assertEquals(expResult2, result2);
    }
    
    /**
     * Test of execRegistry method, of class SoajsRegistry.
     */
    @Test
    public void testExecRegistry() {
        System.out.println("execRegistry");
        
        // invalid format
        SoajsRegistry.soajsRegistryApi = "127.0.0.1";
        boolean expResult = false;
        boolean result = SoajsRegistry.execRegistry();
        assertEquals(expResult, result);
        
        // invalid port format
        SoajsRegistry.soajsRegistryApi = "127.0.0.1:sss";
        boolean expResult2 = false;
        boolean result2 = SoajsRegistry.execRegistry();
        assertEquals(expResult2, result2);
        
         // valid
        SoajsRegistry.env = "dashboard";
        SoajsRegistry.serviceName = "test";
        SoajsRegistry.setBy = "tester";
        SoajsRegistry.soajsRegistryApi = "127.0.0.1:5000";
        boolean expResult3 = true;
        boolean result3 = SoajsRegistry.execRegistry();
        assertEquals(expResult3, result3);  
    }
    
    /**
     * Test of reload method, of class SoajsRegistry.
     */
    @Test
    public void testReload() {
        System.out.println("reload");
        
        SoajsRegistry.env = "dashboard";
        boolean expResult = true;
        boolean result = SoajsRegistry.reload();
        assertEquals(expResult, result);
        
        SoajsRegistry.env = null;
        boolean expResult2 = false;
        boolean result2 = SoajsRegistry.reload();
        assertEquals(expResult2, result2);
    }
    
    /**
     * Test of execRegistry method, of class SoajsRegistry.
     */
    @Test
    public void testExecRegistryNoController() {
        System.out.println("execRegistry - no controller");
        
        controllerThread.stop();
        SoajsRegistry.soajsRegistryApi = "127.0.0.1:5000";
        boolean expResult4 = false;
        boolean result4 = SoajsRegistry.execRegistry();
        assertEquals(expResult4, result4);
    }
    
}