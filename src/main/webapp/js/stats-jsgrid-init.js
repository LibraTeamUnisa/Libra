! function(document, window, $) {
	
	var MyProgressField = function(config) {
	    jsGrid.Field.call(this, config);
	};
	 
	MyProgressField.prototype = new jsGrid.Field({
	 	 	 
	    sorter: function(value1, value2) {
	        return value1 - value2;
	    },
	 
	    itemTemplate: function(valueField) {
	    	var $progress = $("<div>").attr("class","progress")
	    	var $innerProgress = $("<div>")
	    	var $text = $("<div>")
	    	
	    	if(valueField >= 8){$text.attr("class","text-info text-center"); $innerProgress.attr("class","progress-bar bg-info")}
            else if(valueField >= 6){$text.attr("class","text-megna text-center"); $innerProgress.attr("class","progress-bar bg-megna")}
            else if(valueField >= 4){$text.attr("class","text-warning text-center"); $innerProgress.attr("class","progress-bar bg-warning")}
            else if(valueField >= 2){$text.attr("class","text-warning text-center"); $innerProgress.attr("class","progress-bar bg-warning")}
            else {$text.attr("class","text-error text-center"); $innerProgress.attr("class","progress-bar bg-danger")}
	    	$text.text(valueField+"/10")
	    	
	    	$innerProgress.attr("style","width: "+valueField * 10 + "%" + "; height: 6px;")
	    	$innerProgress.attr("role","progressbar")
	    	$innerProgress.attr("aria-valuenow","25")
	    	$innerProgress.attr("aria-valuemin","0")
	    	$innerProgress.attr("aria-valuemax","100")
	    	
	    	$progress.append($innerProgress)
	    	
	    	$result = $text.add($progress)
	    	
	    	return $result;
	    },
	 
	    insertTemplate: function(valueField) {
	    	var $result = $("<div>")
	    	
	    	if(valueField >= 8){$result.attr("class","text-info")}
            else if(valueField >= 6){$result.attr("class","text-megna")}
            else if(valueField >= 4){$result.attr("class","text-warning")}
            else if(valueField >= 2){$result.attr("class","text-warning")}
            else {$result.attr("class","text-error")}
	    	
	        return this._insertProgress = $result.text(valueField+"/10");
	    },
	 
	    insertValue: function(valueField) {
	    	var $result = $("<span>")
	    	
	    	if(valueField >= 8){$result.attr("class","text-info")}
            else if(valueField >= 6){$result.attr("class","text-megna")}
            else if(valueField >= 4){$result.attr("class","text-warning")}
            else if(valueField >= 2){$result.attr("class","text-warning")}
            else {$result.attr("class","text-error")}
	    	
	        return this._insertProgress = $result.text(valueField+"/10");
	    }
	 
	});
	 
	jsGrid.fields.progress = MyProgressField;
	
    var Site = window.Site;
    $(document).ready(function($) {
            
        }), jsGrid.setDefaults({
            tableClass: "jsgrid-table table table-striped table-hover"
        }), jsGrid.setDefaults("text", {
            _createTextBox: function() {
                return $("<input>").attr("type", "text").attr("class", "form-control input-sm")
            }
        }), jsGrid.setDefaults("number", {
            _createTextBox: function() {
                return $("<input>").attr("type", "number").attr("class", "form-control input-sm")
            }
        }), jsGrid.setDefaults("textarea", {
            _createTextBox: function() {
                return $("<input>").attr("type", "textarea").attr("class", "form-control")
            }
        }), jsGrid.setDefaults("control", {
            _createGridButton: function(cls, tooltip, clickHandler) {
                var grid = this._grid;
                return $("<button>").addClass(this.buttonClass).addClass(cls).attr({
                    type: "button",
                    title: tooltip
                }).on("click", function(e) {
                    clickHandler(grid, e)
                })
            }
        }), jsGrid.setDefaults("select", {
            _createSelect: function() {
                var $result = $("<select>").attr("class", "form-control input-sm"),
                    valueField = this.valueField,
                    textField = this.textField,
                    selectedIndex = this.selectedIndex;
                return $.each(this.items, function(index, item) {
                    var value = valueField ? item[valueField] : index,
                        text = textField ? item[textField] : item,
                        $option = $("<option>").attr("value", value).text(text).appendTo($result);
                    $option.prop("selected", selectedIndex === index)
                }), $result
            }
        }),
        function() {
            $("#basicgrid").jsGrid({
                height: "500px",
                width: "100%",
                filtering: !0,
                sorting: !0,
                paging: !0,
                autoload: !0,
                pageSize: 15,
                pageButtonCount: 5,
                controller: db,
                fields: [{
                    name: "Azienda",
                    type: "select",
                    items: db.aziende,
                    valueField: "Id",
                    textField: "Name"
                }, {
                    name: "Studenti",
                    type: "number",
                    width: 70
                },{
                    name: "Ambito",
                    type: "text"
                }, {
                    name: "Feedback",
                    type: "progress",
                    valueField: "Id",
                    textField: "Valore"
                }]
            })
        }()
}(document, window, jQuery);