/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Model for a User
 */
Ext.define('Splatter.model.UserReference', {
    extend: 'Ext.data.Model',
    requires: ['Splatter.model.Users'],

    fields: [
        'id',
        'handle'
    ],
    belongsTo: 'Users'
});


