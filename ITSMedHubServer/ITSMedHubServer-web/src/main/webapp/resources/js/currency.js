/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready( function(){
    var userLayoutOptions = $.extend(true, {}, defaultLayoutOptions, {
        south: {
            onclose_end: function () { 
                if ($('#details').length==0){
                    $('.resizer-south-closed').append('<div id="details" style="font-size: 11px; font-weight: bold; padding: 5px 5px;">Details</div>');
                }
            }
        },
        center: {
            paneSelector: '#mainContent', 			
            onresize_end:	function () { 
                //$('#gview_list2').height($('#mainContent').height() - 25);
                $('#list2').setGridHeight($('#mainContent').height() - 54);
            //$('#gview_list2').height($('#listUser\\:edtUserList').height() - 64);
            }
        }
    });
                
    createLayout('currency', userLayoutOptions);
                
    $('#list2').jqGrid({
        url:'#{facesContext.externalContext.requestContextPath}/pages/ManagerListServlet',
        datatype: 'json',
        mtype: 'GET',
        //loadonce: true,
        colNames:['ID', 'Name','Login name', 'Added', 'ITS email', 'Work email'],
        colModel :[ 
        {
            name:'id', 
            index:'id', 
            key: true, 
            hidden: true
        },   
        {
            name:'name', 
            index:'name'
        }, 

        {
            name:'loginName', 
            index:'loginName'
        }, 

        {
            name:'added', 
            index:'added'
        } ,
        {
            name:'itsEmail', 
            index:'itsEmail'
        }, 

        {
            name:'workEmail', 
            index:'workEmail'
        } 
        ],
        loadtext: 'Loading...',
        shrinkToFit: false,
        viewrecords: true,
        hidegrid: false,
        height: 140,
        width: 735,
        caption: 'Currencies',
        onSelectRow: function(ids) { 
        /*selectManager(ids);*/
                  
        } ,
        loadComplete: function(data) {
            jQuery('#list2').setSelection (data.userdata.selId, true);
        },
        ondblClickRow: function (rowid,iRow,iCol,e) {
            var data = $('#list2').getRowData(rowid);
        }
                
    });
              
}); 

