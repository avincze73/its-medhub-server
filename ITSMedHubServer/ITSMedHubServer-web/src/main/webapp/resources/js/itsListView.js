/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var outerLayout;

$(document).ready( function() {
    //tabs-2
    //createTabLayout('ItsManager', managerLayoutOptions);
    

    // create the OUTER LAYOUT
    //outerLayout = $('#mybgdiv').layout( layoutSettings_Outer );
    //tabs-1
   /* outerLayout = $('#tabs-1').layout( layoutSettings_Outer );
    var southSelector = '#mysouth1'; // outer-east pane
    var northSelector = '#mynorth1'; // outer-east pane
    $('<span></span>').addClass('pin-button').prependTo( southSelector );
    $('<span></span>').addClass('pin-button').prependTo( northSelector );
    outerLayout.addPinBtn( southSelector +' .pin-button', 'south');
    outerLayout.addPinBtn( northSelector +' .pin-button', 'north');
    $('<span></span>').attr('id', 'south-closer').prependTo( southSelector );
    $('<span></span>').attr('id', 'north-closer').prependTo( northSelector );
    outerLayout.addCloseBtn('#south-closer', 'south');
    outerLayout.addCloseBtn('#north-closer', 'north');*/
                                
    /*outerLayout = $('#tabs-2').layout( managerLayoutOptions );
    southSelector = '#southManagerList'; // outer-east pane
    northSelector = '#northManagerList'; // outer-east pane
    $('<span></span>').addClass('pin-button').prependTo( southSelector );
    $('<span></span>').addClass('pin-button').prependTo( northSelector );
    outerLayout.addPinBtn( southSelector +' .pin-button', 'south');
    outerLayout.addPinBtn( northSelector +' .pin-button', 'north');
    $('<span></span>').attr('id', 'south-closer').prependTo( southSelector );
    $('<span></span>').attr('id', 'north-closer').prependTo( northSelector );
    outerLayout.addCloseBtn('#south-closer', 'south');
    outerLayout.addCloseBtn('#north-closer', 'north');*/
    
    //tabs-3
    /*outerLayout = $('#tabs-3').layout( layoutSettings_Outer );
    southSelector = '#mysouth3'; // outer-east pane
    northSelector = '#mynorth3'; // outer-east pane
    $('<span></span>').addClass('pin-button').prependTo( southSelector );
    $('<span></span>').addClass('pin-button').prependTo( northSelector );
    outerLayout.addPinBtn( southSelector +' .pin-button', 'south');
    outerLayout.addPinBtn( northSelector +' .pin-button', 'north');
    $('<span></span>').attr('id', 'south-closer').prependTo( southSelector );
    $('<span></span>').attr('id', 'north-closer').prependTo( northSelector );
    outerLayout.addCloseBtn('#south-closer', 'south');
    outerLayout.addCloseBtn('#north-closer', 'north');*/
    
    //tabs-4
    /*outerLayout = $('#tabs-4').layout( layoutSettings_Outer );
    southSelector = '#mysouth4'; // outer-east pane
    northSelector = '#mynorth4'; // outer-east pane
    $('<span></span>').addClass('pin-button').prependTo( southSelector );
    $('<span></span>').addClass('pin-button').prependTo( northSelector );
    outerLayout.addPinBtn( southSelector +' .pin-button', 'south');
    outerLayout.addPinBtn( northSelector +' .pin-button', 'north');
    $('<span></span>').attr('id', 'south-closer').prependTo( southSelector );
    $('<span></span>').attr('id', 'north-closer').prependTo( northSelector );
    outerLayout.addCloseBtn('#south-closer', 'south');
    outerLayout.addCloseBtn('#north-closer', 'north');*/

    // DEMO HELPER: prevent hyperlinks from reloading page when a 'base.href' is set
    $('a').each(function () {
        var path = document.location.href;
        if (path.substr(path.length-1)=='#') path = path.substr(0,path.length-1);
        if (this.href.substr(this.href.length-1) == '#') this.href = path +'#';
    });
});

function createTabLayout(tabname, options){
    //alert(tabname);
    //alert(options);
    outerLayout = $('#tab' + tabname).layout( options );
    southSelector = '#south' + tabname ; // outer-east pane
    northSelector = '#north' + tabname; // outer-east pane
    $('<span></span>').addClass('pin-button').prependTo( southSelector );
    $('<span></span>').addClass('pin-button').prependTo( northSelector );
    outerLayout.addPinBtn( southSelector +' .pin-button', 'south');
    outerLayout.addPinBtn( northSelector +' .pin-button', 'north');
    $('<span></span>').attr('id', 'south-closer').prependTo( southSelector );
    $('<span></span>').attr('id', 'north-closer').prependTo( northSelector );
    outerLayout.addCloseBtn('#south-closer', 'south');
    outerLayout.addCloseBtn('#north-closer', 'north');
}

var defaultLayoutOptions = {
    name: 'outerLayout',
    defaults: {
        size: 'auto',
        minSize: 50,
        paneClass: 'pane', 		// default = 'ui-layout-pane'
        resizerClass: 'resizer',	// default = 'ui-layout-resizer'
        togglerClass: 'toggler',	// default = 'ui-layout-toggler'
        buttonClass: 'button',	// default = 'ui-layout-button'
        contentSelector: '.content',	// inner div to auto-size so only it scrolls, not the entire pane!
        contentIgnoreSelector: 'span',		// 'paneSelector' for content to 'ignore' when measuring room for content
        togglerLength_open: 35,			// WIDTH of toggler on north/south edges - HEIGHT on east/west edges
        togglerLength_closed: 21,			// '100%' OR -1 = full height
        hideTogglerOnSlide: true,		// hide the toggler when pane is 'slid open'
        togglerTip_open: 'Close This Pane',
        togglerTip_closed: 'Open This Pane',
        resizerTip: 'Resize This Pane',
        resizeWhileDragging: true,
        //	effect defaults - overridden on some panes
        fxName: 'slide',		// none, slide, drop, scale
        fxSpeed_open: 750,
        fxSpeed_close: 1500,
        fxSettings_open: {
            easing: 'easeInQuint'
        },
        fxSettings_close: {
            easing: 'easeOutQuint'
        },
        resizeWithWindowDelay:	250
    },
    south: {
        size: 150,
        maxSize: 200,
        spacing_closed:	24,			// HIDE resizer toggler when 'closed'
        resizeWithWindowDelay:	250,
        slidable: false,		// REFERENCE - cannot slide if spacing_closed = 0
        togglerLength_open: 0,			// NONE - using custom togglers INSIDE west-pane
        togglerAlign_closed: 'right',		// align to top of resizer
        fxName : 'none'
    },
    north:{
        size: 90,
        spacing_open: 1,			// cosmetic spacing
        spacing_closed: 26,			// HIDE resizer toggler when 'closed'
        resizable: false,
        slidable: false,
        togglerAlign_closed: 'right',		// align to top of resizer
        fxName: 'none'
    }, 
    center: {
        onresize: 'innerLayout.resizeAll',	// resize INNER LAYOUT when center pane resizes
        minWidth: 200,
        minHeight: 150
    }
    };
       
       
    
  
  
/*--------------------------------------------------------------------------*/       
var layoutSettings_Outer = {
    name: 'outerLayout', // NO FUNCTIONAL USE, but could be used by custom code to 'identify' a layout
    // options.defaults apply to ALL PANES - but overridden by pane-specific settings
    defaults: {
        size: 'auto',
        minSize: 50,
        paneClass: 'pane', 		// default = 'ui-layout-pane'
        resizerClass: 'resizer',	// default = 'ui-layout-resizer'
        togglerClass: 'toggler',	// default = 'ui-layout-toggler'
        buttonClass: 'button',	// default = 'ui-layout-button'
        contentSelector: '.content',	// inner div to auto-size so only it scrolls, not the entire pane!
        contentIgnoreSelector: 'span',		// 'paneSelector' for content to 'ignore' when measuring room for content
        togglerLength_open: 35,			// WIDTH of toggler on north/south edges - HEIGHT on east/west edges
        togglerLength_closed: 21,			// '100%' OR -1 = full height
        hideTogglerOnSlide: true,		// hide the toggler when pane is 'slid open'
        togglerTip_open: 'Close This Pane',
        togglerTip_closed: 'Open This Pane',
        resizerTip: 'Resize This Pane',
        resizeWhileDragging: true,
        //	effect defaults - overridden on some panes
        fxName: 'slide',		// none, slide, drop, scale
        fxSpeed_open: 750,
        fxSpeed_close: 1500,
        fxSettings_open: {
            easing: 'easeInQuint'
        },
        fxSettings_close: {
            easing: 'easeOutQuint'
        },
        resizeWithWindowDelay:	250
    },
    north: {
        size: 80,
        spacing_open: 1,			// cosmetic spacing
        spacing_closed: 26,			// HIDE resizer toggler when 'closed'
        resizable: false,
        slidable: false,
        togglerAlign_closed: 'right',		// align to top of resizer
        fxName: 'none',
        onclose_end: function () { 
            if ($('#searchManagerList').length==0){
                $('.resizer-north-closed').append('<div id="searchManagerList" style="font-size: 11px; font-weight: bold; padding: 1px 5px;">Search</div>');
            }
        },
        onopen_end: function () { 
        }
    },
    south: {
        size: 150,
        maxSize: 200,
        spacing_closed:	24,			// HIDE resizer toggler when 'closed'
        resizeWithWindowDelay:	250,
        slidable: false,		// REFERENCE - cannot slide if spacing_closed = 0
        togglerLength_open: 0,			// NONE - using custom togglers INSIDE west-pane
        togglerAlign_closed: 'right',		// align to top of resizer
        fxName:	'none',
        onclose_end: function () { 
            if ($('#detailsManagerList').length==0){
                $('.resizer-south-closed').append('<div id="detailsManagerList" style="font-size: 11px; font-weight: bold; padding: 3px 5px;">Details</div>');
            }
        },
        onopen_end: function () { 
        }
    },
    center: {
        paneSelector: '#mainContentManagerList', 			// sample: use an ID to select pane instead of a class
        onresize: 'innerLayout.resizeAll',	// resize INNER LAYOUT when center pane resizes
        minWidth: 200,
        minHeight: 150,
        onresize_end:	function () { 
            $('#list\\:edtManagerList').height($('#mainContentManagerList').height() - 25);
            $('#list\\:edtManagerList\\:b').height($('#list\\:edtManagerList').height() - 64);
        }
    }
};
