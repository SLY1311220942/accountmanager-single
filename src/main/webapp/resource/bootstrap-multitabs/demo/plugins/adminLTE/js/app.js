/*! AdminLTE app.js
 * ================
 * Main JS application file for AdminLTE v2. This file
 * should be included in all pages. It controls some layout
 * options and implements exclusive AdminLTE plugins.
 *
 * @Author  Almsaeed Studio
 * @Support <http://www.almsaeedstudio.com>
 * @Email   <abdullah@almsaeedstudio.com>
 * @version 2.3.6
 * @license MIT <http://opensource.org/licenses/MIT>
 */

//Make sure jQuery has been loaded before app.js
if(typeof jQuery === "undefined") {
	throw new Error("AdminLTE requires jQuery");
}

/* AdminLTE
 *
 * @type Object
 * @description $.AdminLTE is the main object for the template's app.
 *              It's used for implementing functions and options related
 *              to the template. Keeping everything wrapped in an object
 *              prevents conflict with other plugins and is a better
 *              way to organize our code.
 */
$.AdminLTE = {};

/* --------------------
 * - AdminLTE Options -
 * --------------------
 * Modify these options to suit your implementation
 */
$.AdminLTE.options = {
	//Add slimscroll to navbar menus
	//This requires you to load the slimscroll plugin
	//in every page before app.js
	navbarMenuSlimscroll: true,
	navbarMenuSlimscrollWidth: "3px", //The width of the scroll bar
	navbarMenuHeight: "200px", //The height of the inner menu
	//General animation speed for JS animated elements such as box collapse/expand and
	//sidebar treeview slide up/down. This options accepts an integer as milliseconds,
	//'fast', 'normal', or 'slow'
	animationSpeed: 500,
	//Sidebar push menu toggle button selector
	sidebarToggleSelector: "[data-toggle='offcanvas']",
	//Activate sidebar push menu
	sidebarPushMenu: true,
	//Activate sidebar slimscroll if the fixed layout is set (requires SlimScroll Plugin)
	sidebarSlimScroll: true,
	//Enable sidebar expand on hover effect for sidebar mini
	//This option is forced to true if both the fixed layout and sidebar mini
	//are used together
	sidebarExpandOnHover: false,
	//BoxRefresh Plugin
	enableBoxRefresh: true,
	//Bootstrap.js tooltip
	enableBSToppltip: true,
	BSTooltipSelector: "[data-toggle='tooltip']",
	//Enable Fast Click. Fastclick.js creates a more
	//native touch experience with touch devices. If you
	//choose to enable the plugin, make sure you load the script
	//before AdminLTE's app.js
	enableFastclick: false,
	//Control Sidebar Options
	enableControlSidebar: true,
	controlSidebarOptions: {
		//Which button should trigger the open/close event
		toggleBtnSelector: "[data-toggle='control-sidebar']",
		//The sidebar selector
		selector: ".control-sidebar",
		//Enable slide over content
		slide: true
	},
	//Box Widget Plugin. Enable this plugin
	//to allow boxes to be collapsed and/or removed
	enableBoxWidget: true,
	//Box Widget plugin options
	boxWidgetOptions: {
		boxWidgetIcons: {
			//Collapse icon
			collapse: 'fa-minus',
			//Open icon
			open: 'fa-plus',
			//Remove icon
			remove: 'fa-times'
		},
		boxWidgetSelectors: {
			//Remove button selector
			remove: '[data-widget="remove"]',
			//Collapse button selector
			collapse: '[data-widget="collapse"]'
		}
	},
	//Direct Chat plugin options
	directChat: {
		//Enable direct chat by default
		enable: true,
		//The button to open and close the chat contacts pane
		contactToggleSelector: '[data-widget="chat-pane-toggle"]'
	},
	//Define the set of colors to use globally around the website
	colors: {
		lightBlue: "#3c8dbc",
		red: "#f56954",
		green: "#00a65a",
		aqua: "#00c0ef",
		yellow: "#f39c12",
		blue: "#0073b7",
		navy: "#001F3F",
		teal: "#39CCCC",
		olive: "#3D9970",
		lime: "#01FF70",
		orange: "#FF851B",
		fuchsia: "#F012BE",
		purple: "#8E24AA",
		maroon: "#D81B60",
		black: "#222222",
		gray: "#d2d6de"
	},
	//The standard screen sizes that bootstrap uses.
	//If you change these in the variables.less file, change
	//them here too.
	screenSizes: {
		xs: 480,
		sm: 768,
		md: 992,
		lg: 1200
	}
};

/* ------------------
 * - Implementation -
 * ------------------
 * The next block of code implements AdminLTE's
 * functions and plugins as specified by the
 * options above.
 */
$(function() {
	"use strict";

	//Fix for IE page transitions
	$("body").removeClass("hold-transition");

	//Extend options if external options exist
	if(typeof AdminLTEOptions !== "undefined") {
		$.extend(true,
			$.AdminLTE.options,
			AdminLTEOptions);
	}

	//Easy access to options
	var o = $.AdminLTE.options;

	//Set up the object
	_init();

	//Activate the layout maker
	$.AdminLTE.layout.activate();

	//Enable sidebar tree view controls
	//$.AdminLTE.tree('.sidebar');

	//Enable control sidebar
	if(o.enableControlSidebar) {
		$.AdminLTE.controlSidebar.activate();
	}

	//Add slimscroll to navbar dropdown
	if(o.navbarMenuSlimscroll && typeof $.fn.slimscroll != 'undefined') {
		$(".navbar .menu").slimscroll({
			height: o.navbarMenuHeight,
			alwaysVisible: false,
			size: o.navbarMenuSlimscrollWidth
		}).css("width", "100%");
	}

	//Activate sidebar push menu
	/*if (o.sidebarPushMenu) {
	  $.AdminLTE.pushMenu.activate(o.sidebarToggleSelector);
	}*/

	//Activate Bootstrap tooltip
	if(o.enableBSToppltip) {
		$('body').tooltip({
			selector: o.BSTooltipSelector
		});
	}

	//Activate box widget
	if(o.enableBoxWidget) {
		$.AdminLTE.boxWidget.activate();
	}

	//Activate fast click
	if(o.enableFastclick && typeof FastClick != 'undefined') {
		FastClick.attach(document.body);
	}

	//Activate direct chat widget
	if(o.directChat.enable) {
		$(document).on('click', o.directChat.contactToggleSelector, function() {
			var box = $(this).parents('.direct-chat').first();
			box.toggleClass('direct-chat-contacts-open');
		});
	}

	/*
	 * INITIALIZE BUTTON TOGGLE
	 * ------------------------
	 */
	$('.btn-group[data-toggle="btn-toggle"]').each(function() {
		var group = $(this);
		$(this).find(".btn").on('click', function(e) {
			group.find(".btn.active").removeClass("active");
			$(this).addClass("active");
			e.preventDefault();
		});

	});
});

/* ----------------------------------
 * - Initialize the AdminLTE Object -
 * ----------------------------------
 * All AdminLTE functions are implemented below.
 */
function _init() {
	'use strict';
	/* Layout
	 * ======
	 * Fixes the layout height in case min-height fails.
	 *
	 * @type Object
	 * @usage $.AdminLTE.layout.activate()
	 *        $.AdminLTE.layout.fix()
	 *        $.AdminLTE.layout.fixSidebar()
	 */
	$.AdminLTE.layout = {
		activate: function() {
			var _this = this;
			_this.fix();
			_this.fixSidebar();
			$(window, ".wrapper").resize(function() {
				_this.fix();
				_this.fixSidebar();
			});
		},
		fix: function() {
			//Get window height and the wrapper height
			var neg = $('.main-header').outerHeight() + $('.main-footer').outerHeight();
			var window_height = $(window).height();
			var sidebar_height = $(".sidebar").height();
			//Set the min-height of the content and sidebar based on the
			//the height of the document.
			if($("body").hasClass("fixed")) {
				$(".content-wrapper, .right-side").css('min-height', window_height - $('.main-footer').outerHeight());
			} else {
				var postSetWidth;
				if(window_height >= sidebar_height) {
					$(".content-wrapper, .right-side").css('min-height', window_height - neg);
					postSetWidth = window_height - neg;
				} else {
					$(".content-wrapper, .right-side").css('min-height', sidebar_height);
					postSetWidth = sidebar_height;
				}

				//Fix for the control sidebar height
				/*var controlSidebar = $($.AdminLTE.options.controlSidebarOptions.selector);
				if(typeof controlSidebar !== "undefined") {
					if(controlSidebar.height() > postSetWidth)
						$(".content-wrapper, .right-side").css('min-height', controlSidebar.height());
				}*/

			}
		},
		fixSidebar: function() {
			//Make sure the body tag has the .fixed class
			if(!$("body").hasClass("fixed")) {
				if(typeof $.fn.slimScroll != 'undefined') {
					$(".sidebar").slimScroll({ destroy: true }).height("auto");
				}
				return;
			} else if(typeof $.fn.slimScroll == 'undefined' && window.console) {
				window.console.error("Error: the fixed layout requires the slimscroll plugin!");
			}
			//Enable slimscroll for fixed layout
			if($.AdminLTE.options.sidebarSlimScroll) {
				if(typeof $.fn.slimScroll != 'undefined') {
					//Destroy if it exists
					$(".sidebar").slimScroll({ destroy: true }).height("auto");
					//Add slimscroll
					$(".sidebar").slimscroll({
						height: ($(window).height() - $(".main-header").height()) + "px",
						color: "rgba(0,0,0,0.2)",
						size: "3px"
					});
				}
			}
		}
	};


	/* ControlSidebar
	 * ==============
	 * Adds functionality to the right sidebar
	 *
	 * @type Object
	 * @usage $.AdminLTE.controlSidebar.activate(options)
	 */
	$.AdminLTE.controlSidebar = {
		//instantiate the object
		activate: function() {
			//Get the object
			var _this = this;
			//Update options
			var o = $.AdminLTE.options.controlSidebarOptions;
			//Get the sidebar
			var sidebar = $(o.selector);
			//The toggle button
			var btn = $(o.toggleBtnSelector);

			//Listen to the click event
			btn.on('click', function(e) {
				e.preventDefault();
				//If the sidebar is not open
				if(!sidebar.hasClass('control-sidebar-open') &&
					!$('body').hasClass('control-sidebar-open')) {
					//Open the sidebar
					_this.open(sidebar, o.slide);
				} else {
					_this.close(sidebar, o.slide);
				}
			});

			//If the body has a boxed layout, fix the sidebar bg position
			var bg = $(".control-sidebar-bg");
			_this._fix(bg);

			//If the body has a fixed layout, make the control sidebar fixed
			if($('body').hasClass('fixed')) {
				_this._fixForFixed(sidebar);
			} else {
				//If the content height is less than the sidebar's height, force max height
				if($('.content-wrapper, .right-side').height() < sidebar.height()) {
					_this._fixForContent(sidebar);
				}
			}
		},
		//Open the control sidebar
		open: function(sidebar, slide) {
			//Slide over content
			if(slide) {
				sidebar.addClass('control-sidebar-open');
			} else {
				//Push the content by adding the open class to the body instead
				//of the sidebar itself
				$('body').addClass('control-sidebar-open');
			}
		},
		//Close the control sidebar
		close: function(sidebar, slide) {
			if(slide) {
				sidebar.removeClass('control-sidebar-open');
			} else {
				$('body').removeClass('control-sidebar-open');
			}
		},
		_fix: function(sidebar) {
			var _this = this;
			if($("body").hasClass('layout-boxed')) {
				sidebar.css('position', 'absolute');
				sidebar.height($(".wrapper").height());
				if(_this.hasBindedResize) {
					return;
				}
				$(window).resize(function() {
					_this._fix(sidebar);
				});
				_this.hasBindedResize = true;
			} else {
				sidebar.css({
					'position': 'fixed',
					'height': 'auto'
				});
			}
		},
		_fixForFixed: function(sidebar) {
			sidebar.css({
				'position': 'fixed',
				'max-height': '100%',
				'overflow': 'auto',
				'padding-bottom': '50px'
			});
		},
		_fixForContent: function(sidebar) {
			$(".content-wrapper, .right-side").css('min-height', sidebar.height());
		}
	};

	/* BoxWidget
	 * =========
	 * BoxWidget is a plugin to handle collapsing and
	 * removing boxes from the screen.
	 *
	 * @type Object
	 * @usage $.AdminLTE.boxWidget.activate()
	 *        Set all your options in the main $.AdminLTE.options object
	 */
	$.AdminLTE.boxWidget = {
		selectors: $.AdminLTE.options.boxWidgetOptions.boxWidgetSelectors,
		icons: $.AdminLTE.options.boxWidgetOptions.boxWidgetIcons,
		animationSpeed: $.AdminLTE.options.animationSpeed,
		activate: function(_box) {
			var _this = this;
			if(!_box) {
				_box = document; // activate all boxes per default
			}
			//Listen for collapse event triggers
			$(_box).on('click', _this.selectors.collapse, function(e) {
				e.preventDefault();
				_this.collapse($(this));
			});

			//Listen for remove event triggers
			$(_box).on('click', _this.selectors.remove, function(e) {
				e.preventDefault();
				_this.remove($(this));
			});
		},
		collapse: function(element) {
			var _this = this;
			//Find the box parent
			var box = element.parents(".box").first();
			//Find the body and the footer
			var box_content = box.find("> .box-body, > .box-footer, > form  >.box-body, > form > .box-footer");
			if(!box.hasClass("collapsed-box")) {
				//Convert minus into plus
				element.children(":first")
					.removeClass(_this.icons.collapse)
					.addClass(_this.icons.open);
				//Hide the content
				box_content.slideUp(_this.animationSpeed, function() {
					box.addClass("collapsed-box");
				});
			} else {
				//Convert plus into minus
				element.children(":first")
					.removeClass(_this.icons.open)
					.addClass(_this.icons.collapse);
				//Show the content
				box_content.slideDown(_this.animationSpeed, function() {
					box.removeClass("collapsed-box");
				});
			}
		},
		remove: function(element) {
			//Find the box parent
			var box = element.parents(".box").first();
			box.slideUp(this.animationSpeed);
		}
	};
}


(function($) {

	"use strict";

	$.fn.boxRefresh = function(options) {

		// Render options
		var settings = $.extend({
			//Refresh button selector
			trigger: ".refresh-btn",
			//File source to be loaded (e.g: ajax/src.php)
			source: "",
			//Callbacks
			onLoadStart: function(box) {
				return box;
			}, //Right after the button has been clicked
			onLoadDone: function(box) {
				return box;
			} //When the source has been loaded

		}, options);

		//The overlay
		var overlay = $('<div class="overlay"><div class="fa fa-refresh fa-spin"></div></div>');

		return this.each(function() {
			//if a source is specified
			if(settings.source === "") {
				if(window.console) {
					window.console.log("Please specify a source first - boxRefresh()");
				}
				return;
			}
			//the box
			var box = $(this);
			//the button
			var rBtn = box.find(settings.trigger).first();

			//On trigger click
			rBtn.on('click', function(e) {
				e.preventDefault();
				//Add loading overlay
				start(box);

				//Perform ajax call
				box.find(".box-body").load(settings.source, function() {
					done(box);
				});
			});
		});

		function start(box) {
			//Add overlay and loading img
			box.append(overlay);

			settings.onLoadStart.call(box);
		}

		function done(box) {
			//Remove overlay and loading img
			box.find(overlay).remove();

			settings.onLoadDone.call(box);
		}

	};

})(jQuery);


(function($) {

	'use strict';

	$.fn.activateBox = function() {
		$.AdminLTE.boxWidget.activate(this);
	};

	$.fn.toggleBox = function() {
		var button = $($.AdminLTE.boxWidget.selectors.collapse, this);
		$.AdminLTE.boxWidget.collapse(button);
	};

	$.fn.removeBox = function() {
		var button = $($.AdminLTE.boxWidget.selectors.remove, this);
		$.AdminLTE.boxWidget.remove(button);
	};

})(jQuery);


(function($) {

	'use strict';

	$.fn.todolist = function(options) {
		// Render options
		var settings = $.extend({
			//When the user checks the input
			onCheck: function(ele) {
				return ele;
			},
			//When the user unchecks the input
			onUncheck: function(ele) {
				return ele;
			}
		}, options);

		return this.each(function() {

			if(typeof $.fn.iCheck != 'undefined') {
				$('input', this).on('ifChecked', function() {
					var ele = $(this).parents("li").first();
					ele.toggleClass("done");
					settings.onCheck.call(ele);
				});

				$('input', this).on('ifUnchecked', function() {
					var ele = $(this).parents("li").first();
					ele.toggleClass("done");
					settings.onUncheck.call(ele);
				});
			} else {
				$('input', this).on('change', function() {
					var ele = $(this).parents("li").first();
					ele.toggleClass("done");
					if($('input', ele).is(":checked")) {
						settings.onCheck.call(ele);
					} else {
						settings.onUncheck.call(ele);
					}
				});
			}
		});
	};
}(jQuery));


+function ($) {
  'use strict';

  var DataKey = 'lte.tree';

  var Default = {
    animationSpeed: 500,
    accordion     : true,
    followLink    : false,
    trigger       : '.treeview a'
  };

  var Selector = {
    tree        : '.tree',
    treeview    : '.treeview',
    treeviewMenu: '.treeview-menu',
    open        : '.menu-open, .active',
    li          : 'li',
    data        : '[data-widget="tree"]',
    active      : '.active'
  };

  var ClassName = {
    open: 'menu-open',
    tree: 'tree'
  };

  var Event = {
    collapsed: 'collapsed.tree',
    expanded : 'expanded.tree'
  };

  // Tree Class Definition
  // =====================
  var Tree = function (element, options) {
    this.element = element;
    this.options = options;

    $(this.element).addClass(ClassName.tree);

    $(Selector.treeview + Selector.active, this.element).addClass(ClassName.open);

    this._setUpListeners();
  };

  Tree.prototype.toggle = function (link, event) {
    var treeviewMenu = link.next(Selector.treeviewMenu);
    var parentLi     = link.parent();
    var isOpen       = parentLi.hasClass(ClassName.open);

    if (!parentLi.is(Selector.treeview)) {
      return;
    }

    if (!this.options.followLink || link.attr('href') === '#') {
      event.preventDefault();
    }

    if (isOpen) {
      this.collapse(treeviewMenu, parentLi);
    } else {
      this.expand(treeviewMenu, parentLi);
    }
  };

  Tree.prototype.expand = function (tree, parent) {
    var expandedEvent = $.Event(Event.expanded);

    if (this.options.accordion) {
      var openMenuLi = parent.siblings(Selector.open);
      var openTree   = openMenuLi.children(Selector.treeviewMenu);
      this.collapse(openTree, openMenuLi);
    }

    parent.addClass(ClassName.open);
    tree.slideDown(this.options.animationSpeed, function () {
      $(this.element).trigger(expandedEvent);
    }.bind(this));
  };

  Tree.prototype.collapse = function (tree, parentLi) {
    var collapsedEvent = $.Event(Event.collapsed);

    //tree.find(Selector.open).removeClass(ClassName.open);
    parentLi.removeClass(ClassName.open);
    tree.slideUp(this.options.animationSpeed, function () {
      //tree.find(Selector.open + ' > ' + Selector.treeview).slideUp();
      $(this.element).trigger(collapsedEvent);
    }.bind(this));
  };

  // Private

  Tree.prototype._setUpListeners = function () {
    var that = this;

    $(this.element).on('click', this.options.trigger, function (event) {
      that.toggle($(this), event);
    });
  };

  // Plugin Definition
  // =================
  function Plugin(option) {
    return this.each(function () {
      var $this = $(this);
      var data  = $this.data(DataKey);

      if (!data) {
        var options = $.extend({}, Default, $this.data(), typeof option == 'object' && option);
        $this.data(DataKey, new Tree($this, options));
      }
    });
  }

  var old = $.fn.tree;

  $.fn.tree             = Plugin;
  $.fn.tree.Constructor = Tree;

  // No Conflict Mode
  // ================
  $.fn.tree.noConflict = function () {
    $.fn.tree = old;
    return this;
  };

  // Tree Data API
  // =============
  $(window).on('load', function () {
    $(Selector.data).each(function () {
      Plugin.call($(this));
    });
  });

}(jQuery);


+
function($) {
	'use strict';

	var DataKey = 'lte.pushmenu';

	var Default = {
		collapseScreenSize: 767,
		expandOnHover: false,
		expandTransitionDelay: 200
	};

	var Selector = {
		collapsed: '.sidebar-collapse',
		open: '.sidebar-open',
		mainSidebar: '.main-sidebar',
		contentWrapper: '.content-wrapper',
		searchInput: '.sidebar-form .form-control',
		button: '[data-toggle="push-menu"]',
		mini: '.sidebar-mini',
		expanded: '.sidebar-expanded-on-hover',
		layoutFixed: '.fixed'
	};

	var ClassName = {
		collapsed: 'sidebar-collapse',
		open: 'sidebar-open',
		mini: 'sidebar-mini',
		expanded: 'sidebar-expanded-on-hover',
		expandFeature: 'sidebar-mini-expand-feature',
		layoutFixed: 'fixed'
	};

	var Event = {
		expanded: 'expanded.pushMenu',
		collapsed: 'collapsed.pushMenu'
	};

	// PushMenu Class Definition
	// =========================
	var PushMenu = function(options) {
		this.options = options;
		this.init();
	};

	PushMenu.prototype.init = function() {
		if(this.options.expandOnHover ||
			($('body').is(Selector.mini + Selector.layoutFixed))) {
			this.expandOnHover();
			$('body').addClass(ClassName.expandFeature);
		}

		$(Selector.contentWrapper).click(function() {
			// Enable hide menu when clicking on the content-wrapper on small screens
			if($(window).width() <= this.options.collapseScreenSize && $('body').hasClass(ClassName.open)) {
				this.close();
			}
		}.bind(this));

		// __Fix for android devices
		$(Selector.searchInput).click(function(e) {
			e.stopPropagation();
		});
	};

	PushMenu.prototype.toggle = function() {
		var windowWidth = $(window).width();
		var isOpen = !$('body').hasClass(ClassName.collapsed);

		if(windowWidth <= this.options.collapseScreenSize) {
			isOpen = $('body').hasClass(ClassName.open);
		}

		if(!isOpen) {
			this.open();
		} else {
			this.close();
		}
	};

	PushMenu.prototype.open = function() {
		var windowWidth = $(window).width();

		if(windowWidth > this.options.collapseScreenSize) {
			$('body').removeClass(ClassName.collapsed)
				.trigger($.Event(Event.expanded));
		} else {
			$('body').addClass(ClassName.open)
				.trigger($.Event(Event.expanded));
		}
	};

	PushMenu.prototype.close = function() {
		var windowWidth = $(window).width();
		if(windowWidth > this.options.collapseScreenSize) {
			$('body').addClass(ClassName.collapsed)
				.trigger($.Event(Event.collapsed));
		} else {
			$('body').removeClass(ClassName.open + ' ' + ClassName.collapsed)
				.trigger($.Event(Event.collapsed));
		}
	};

	PushMenu.prototype.expandOnHover = function() {
		$(Selector.mainSidebar).hover(function() {
			if($('body').is(Selector.mini + Selector.collapsed) &&
				$(window).width() > this.options.collapseScreenSize) {
				this.expand();
			}
		}.bind(this), function() {
			if($('body').is(Selector.expanded)) {
				this.collapse();
			}
		}.bind(this));
	};

	PushMenu.prototype.expand = function() {
		setTimeout(function() {
			$('body').removeClass(ClassName.collapsed)
				.addClass(ClassName.expanded);
		}, this.options.expandTransitionDelay);
	};

	PushMenu.prototype.collapse = function() {
		setTimeout(function() {
			$('body').removeClass(ClassName.expanded)
				.addClass(ClassName.collapsed);
		}, this.options.expandTransitionDelay);
	};

	// PushMenu Plugin Definition
	// ==========================
	function Plugin(option) {
		return this.each(function() {
			var $this = $(this);
			var data = $this.data(DataKey);

			if(!data) {
				var options = $.extend({}, Default, $this.data(), typeof option == 'object' && option);
				$this.data(DataKey, (data = new PushMenu(options)));
			}

			if(option === 'toggle') data.toggle();
		});
	}

	var old = $.fn.pushMenu;

	$.fn.pushMenu = Plugin;
	$.fn.pushMenu.Constructor = PushMenu;

	// No Conflict Mode
	// ================
	$.fn.pushMenu.noConflict = function() {
		$.fn.pushMenu = old;
		return this;
	};

	// Data API
	// ========
	$(document).on('click', Selector.button, function(e) {
		e.preventDefault();
		Plugin.call($(this), 'toggle');
	});
	$(window).on('load', function() {
		Plugin.call($(Selector.button));
	});
}(jQuery);