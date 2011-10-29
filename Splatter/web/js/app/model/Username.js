/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Model for a Username
 */
Ext.define('Splatter.model.Username', {
    extend: 'Ext.data.Model',
    requires: ['Splatter.model.User', 'Ext.data.BelongsToAssociation'],

    fields: [
        'first',
        'middle',
        'last',        
    ],
    
    belongsTo: 'User'
});
