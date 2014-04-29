/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var outerLayout;

$(document).ready( function() {
    $('a').each(function () {
        var path = document.location.href;
        if (path.substr(path.length-1)=='#') path = path.substr(0,path.length-1);
        if (this.href.substr(this.href.length-1) == '#') this.href = path +'#';
    });
});

function createLayout(name, options){
    outerLayout = $('#' + name).layout( options );
    southSelector = '#south' ; // outer-east pane
    $('<span></span>').addClass('pin-button').prependTo( southSelector );
    outerLayout.addPinBtn( southSelector +' .pin-button', 'south');
    $('<span></span>').attr('id', 'south-closer').prependTo( southSelector );
    outerLayout.addCloseBtn('#south-closer', 'south');
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
       
       
    
  