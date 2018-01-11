var initTable = () => {
	
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
	    	
	    	if(valueField >= 8/2){$text.attr("class","text-info text-center"); $innerProgress.attr("class","progress-bar bg-info")}
            else if(valueField >= 6/2){$text.attr("class","text-megna text-center"); $innerProgress.attr("class","progress-bar bg-megna")}
            else if(valueField >= 4/2){$text.attr("class","text-warning text-center"); $innerProgress.attr("class","progress-bar bg-warning")}
            else if(valueField >= 2/2){$text.attr("class","text-warning text-center"); $innerProgress.attr("class","progress-bar bg-warning")}
            else if(valueField >= 0){$text.attr("class","text-error text-center"); $innerProgress.attr("class","progress-bar bg-danger")}
            else return "<span class='label label-light-danger col-12 text-center'>Nessun feedback rilasciato</span>";
	    	$text.text(valueField+"/5")
	    	
	    	$innerProgress.attr("style","width: "+valueField *2 * 10 + "%" + "; height: 6px;")
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
	    	
	    	if(valueField >= 8/2){$result.attr("class","text-info")}
            else if(valueField >= 6/2){$result.attr("class","text-megna")}
            else if(valueField >= 4/2){$result.attr("class","text-warning")}
            else if(valueField >= 2/2){$result.attr("class","text-warning")}
            else {$result.attr("class","text-error")}
	    	
	        return this._insertProgress = $result.text(valueField+"/5");
	    },
	 
	    insertValue: function(valueField) {
	    	var $result = $("<div>")
	    	
	    	if(valueField >= 8/2){$result.attr("class","text-info")}
            else if(valueField >= 6/2){$result.attr("class","text-megna")}
            else if(valueField >= 4/2){$result.attr("class","text-warning")}
            else if(valueField >= 2/2){$result.attr("class","text-warning")}
            else if(valueField >= 0) {$result.attr("class","text-error")}
            else {}
	    	
	        return this._insertProgress = $result.text(valueField+"/5");
	    }
	 
	});
	 
	jsGrid.fields.progress = MyProgressField;
	
	// START AMBITO FIELD
	
	var MyAmbitoField = function(config) {
	    jsGrid.Field.call(this, config);
	};
	 
	MyAmbitoField.prototype = new jsGrid.Field({
	 	 	 
	    sorter: function(value1, value2) {
	        return value1.localeCompare(value2);
	    },
	 
	    itemTemplate: function(valueField) {
	    	var $result = $("<span>");
	    	$result.attr("class","label label-light-info");
	    	$result.text(valueField);
	    	return $result;
	    },
	 
	    insertTemplate: function(valueField) {
	    	var $result = $("<span>");
	    	$result.attr("class","label label-light-info");
	    	$result.text(valueField);
	        return this._insertAmbito = $result;
	    },
	 
	    insertValue: function(valueField) {
	        return this._insertAmbito.val();
	    },
	    
	    filterTemplate: function(value) { 
	    	return this._filterAmbito = $("<input>").attr("type", "text").attr("class", "form-control input-sm").val(value); 
	    }, 
	    
	    filterValue : function() { 
	    	return this._filterAmbito.val(); 
	    },
	});
	 
	jsGrid.fields.ambito = MyAmbitoField;
	
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
                    type: "text"
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
};