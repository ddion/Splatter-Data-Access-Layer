/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Model for a User
 */
Ext.define('Splatter.model.User', {
    extend: 'Ext.data.Model',
    requires: ['Splatter.model.Username'],

    fields: [
        'id',
        'handle',
        'password',
        'email',
        'emailPrivacy',
        'userPrivacy',
    ],
    associations: [ 
        {type: 'hasMany', model: 'Username', name: 'username'}, 
    ]
});


