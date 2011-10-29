/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Model for a Users
 */
Ext.define('Splatter.model.Users', {
    extend: 'Ext.data.Model',
    requires: ['Splatter.model.UserReference'],
    
    associations: [ 
        {type: 'hasMany', model: 'UserReference', name: 'users'}, 
    ]
});
