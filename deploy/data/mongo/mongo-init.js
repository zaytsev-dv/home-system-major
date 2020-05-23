db.createUser(
    {
        user: "mongo-root",
        pwd: "password",
        roles: [ { role: "root", db: "admin" } ]
    }
);

db.createUser(
    {
        user: "mongo-admin",
        pwd: "password",
        roles: [ { role: "userAdminAnyDatabase", db: "admin" } ]
    }
);

db.createUser(
    {
        user: "notification-admin",
        pwd: "password",
        roles: [ { role: "readWrite", db: "notifications" } ]
    }
);

