db.createUser(
    {
        user: "notification_admin",
        pwd: "qwerty123",
        roles: [
            {
                role: "readWrite",
                db: "notifications"
            }
        ]
    }
);