
db.getSiblingDB('admin').createUser({
  user: 'user',
  pwd: 'pass',
  roles: [{ role: 'root', db: 'admin' }]
});