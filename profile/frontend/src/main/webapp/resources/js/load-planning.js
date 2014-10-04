
var jsLoadSearch = new Object();
jsLoadSearch.type="pa";
var jsTreeState = new Object();
jsTreeState.pageIndex=1;
jsTreeState.changedPurchasedActivities = new Array();
jsTreeState.activeLoad = null;
jsTreeState.jsTreeDragAndDropSource = null;
jsTreeState.jsTreeDragAndDropTarget = null;



$(document).ready(function () {

    pageSetUp();

    $("#filterTreeInput").on("keyup", function () {
        filterTreeNodes($(this).val());
    })
    $(window).resize(function () {
        $(".widget-body.tree-container").css("height", ($(window).height() - 220) + "px");

    });
    $(".widget-body.tree-container").css("height", ($(window).height() - 220) + "px");
    var loadId;
    var globalPurchaseActivitityId;
    $("#btnMove").on("click", function () {
        createPurchasedActivityAndAssignCustomerShipments();
    });

    $('.datepickerModal').datepicker({
        dateFormat: $("#localizedDatePatternWeb").val(),
        onSelect: function (dateText, inst) {
            setPendingReference();
        }
    })

    $('.datepicker').change(function () {
        setModalInputFieldValues();
    });
    var e = jQuery.Event("click");
    // trigger an artificial click event
    jQuery(".datepicker").trigger(e);
    $(".datepicker").on("click", function () {
        var dateDiv = $("#ui-datepicker-div");
        dateDiv.css("left", "160px");
    });
    $('#fromDate').datepicker({
        dateFormat : $('#fromDate').attr("data-dateformat"),
        prevText : '<i class="fa fa-chevron-left"></i>',
        nextText : '<i class="fa fa-chevron-right"></i>',
        onSelect : function(selectedDate) {
            $('#endDate').datepicker('option', 'minDate', selectedDate);
        }
    }).on("click", function(){
        var dateDiv = $("#ui-datepicker-div");
        dateDiv.css("left", "160px");
    });
    $('#endDate').datepicker({
        dateFormat :$('#endDate').attr("data-dateformat"),
        prevText : '<i class="fa fa-chevron-left"></i>',
        nextText : '<i class="fa fa-chevron-right"></i>',
        onSelect : function(selectedDate) {
            $('#fromDate').datepicker('option', 'maxDate', selectedDate);
        }
    }).on("click", function(){
        var dateDiv = $("#ui-datepicker-div");
        dateDiv.css("left", "160px");
    });


    $('#treeLoads').on('refresh.jstree', function (e, data) {
        initializeGoToPagePopover(data);

    });
    $('#treeLoads').on('loaded.jstree', function (e, data) {
        initializeGoToPagePopover(data);
    });

    function initializeGoToPagePopover(data){
        var root=data.instance._model.data.valueOf("#");
        var keys = [ ];
        for (var i in root)
            keys.push(i);

        var id =keys[1];
        var node=data.instance._model.data[id];
        if(node!=undefined && node.type!=undefined && node.type!=undefined &&  node.type=="loads"){


            updatePaginationBar(node.original.begin, node.original.end, node.original.totalRecords, node.original.currentPage, node.original.totalPages);
            $('#pagingPage').popover({
                html: true,
                title: 'Go to page',
                placement: 'top',
                content: '<div style="width:130px"> <label class="select"><select id="pageSelection" name="pageSelection"> </select></label>&nbsp;&nbsp;<button id="btnGoToPage" class="btn btn-primary btn-xs pull-right">Go to page</button></div>'

            }).on("shown.bs.popover", function(){
                $("#btnGoToPage").on("click",function(){
                    goToPage();
                });
                populateGoToPageDropdown();
            });

            $('#treeLoads').on('show_contextmenu.jstree', function (e, node) {
                $(".vakata-context.jstree-contextmenu.jstree-default-contextmenu").css("left", "60px");


            });

        }

    }
    $('#treeLoads').on('changed.jstree', function (e, data) {
       var node = data.instance.get_node(data.selected[0]);
        if (node != undefined || node != null) {
            hideDetailsWidget();
            if (node.original != undefined && node.original.type == "purchasedactivities" || node.type == "purchasedactivities") {
                loadId = node.parent;
                if (node.icon.indexOf("edited") > 0 || node.icon.indexOf("deleted") > 0 || node.icon.indexOf("moved") > 0) {
                    refreshMovedDetails(node.id, loadId, "purchasedactivities");
                } else {
                    var idsArray = new Array();
                    refreshDetails(loadId, [node.id]);
                }
                jsTreeState.activeLoad = loadId;

            } else if (node.original != undefined && node.original.type == "loads") {
                loadId = data.selected[0];
                jsTreeState.activeLoad = loadId;
                $('#treeLoads').jstree('open_node', data.selected[0], function () {
                    refreshDetails(loadId, $('#treeLoads').jstree('get_node', data.selected[0]).children);
                });

            } else if (node.original != undefined && node.original.type == "customershipments") {
                loadId = node.parents[1]
                globalPurchaseActivitityId = new Array();
                globalPurchaseActivitityId.push(node.parents[0]);

                if (node.originalId == undefined && (node.icon.indexOf("edited") < 0 && node.icon.indexOf("deleted") < 0) && jsTreeState.changedPurchasedActivities[globalPurchaseActivitityId[0]] == undefined || jsTreeState.changedPurchasedActivities[globalPurchaseActivitityId[0]] == false) {
                    refreshDetails(loadId, globalPurchaseActivitityId);
                } else if (node.originalId == undefined && jsTreeState.changedPurchasedActivities[globalPurchaseActivitityId[0]] == true) {
                    refreshMovedDetails(globalPurchaseActivitityId[0], loadId, "customershipments");
                } else {
                    loadId = node.parents[1];
                    if (node.originalPurchasedActivityId != undefined) {
                        globalPurchaseActivitityId = node.originalPurchasedActivityId;
                    } else {
                        globalPurchaseActivitityId = node.parent;
                    }

                    refreshMovedDetails(globalPurchaseActivitityId, loadId, "customershipments");
                }
                jsTreeState.activeLoad = loadId;
            }

        }
    }).jstree({
        'core': {
            'data': {
                'url': function (node) {
                    try {
                        if (node.id === '#') {
                            return '/load-planning/cirrus/v1/loadplanning/viewport/loads.json?' + $("#searchForm").serialize()+"&pageIndex="+jsTreeState.pageIndex;
                        } else if (node.original.type == "purchasedactivities") {
                            return '/load-planning/cirrus/v1/loadplanning/viewport/customershipments/nodes.json';
                        } else if (node.original.type == "loads") {
                            return '/load-planning/cirrus/v1/loadplanning/viewport/purchasedactivities/nodes.json';
                        }
                    } catch (e) {

                    }
                },
                'data': function (node) {
                    if(node.type=="loads" && node.original.begin!=undefined){
                        updatePaginationBar(node.original.begin, node.original.end, node.original.totalRecords, node.original.currentPage, node.original.totalPages);
                    }
                    return { 'id': node.id };
                }
            },
            'check_callback': function (o, n, p, i, m) {

                if (o === "create_node") {
                    return true;
                }
                if (m && m.dnd && m.pos !== 'i') {
                    return false;
                }
                if (m && m.core) {
                    if (p.type = "purchasedactivities") {
                        var inst = $("#treeLoads").jstree();
                        var nodePathForTargetCommaSeparatedString = this.get_path(p.id, ',', true);
                        var nodePathForTargetArray = nodePathForTargetCommaSeparatedString.split(',');
                        this.set_icon(nodePathForTargetArray, 'jstree-folder-edited');
                        var nodePathForSourceCommaSeparatedString = inst.get_path(n.id, ',', true);
                        var nodePathForSourceArray = nodePathForSourceCommaSeparatedString.split(',');
                        this.set_icon(nodePathForSourceArray, 'jstree-folder-edited');
                        this.set_icon(n, 'jstree-customershipment-moved');
                    } else {
                        this.set_icon(n, 'jstree-folder-edited');
                    }
                }

                if (o === "delete_node") {
                    return true;
                }
                if (o === "move_node" || o === "copy_node") {
                    if (this.get_node(n).parent === this.get_node(p).id) {
                        return false;
                    }

                    if (n.original != undefined && n.original.type == "customershipments" && p.type == "loads" || p.type == "purchasedactivities") {
                        if (p.state.opened == true && p.type == "loads") {
                            //refreshDetails(p.id, p.children);

                        } else if (p.state.opened == true && p.type == "purchasedactivities") {
                            //refreshDetails(p.parent, [p.id]);

                        } else if (p.state.opened == false) {
                            $('#treeLoads').jstree('open_node', p, function () {
                                return false;
                            });

                        }
                    }
                    //if(n.original!=undefined && n.original.type=="customershipments" && n.original.type!="purchasedactivities") {

                    if (n.original != undefined && n.original.type == "customershipments" && n.original.type != "purchasedactivities" && p.type != "loads" && p.type != "customershipments" && p.state.opened == true) {
                        p.original = n.original;
                        p.original.originalPurchasedActivityId = n.parent;
                        p.original.originalPurchasedActivityId = n.parent;
                        p.original.originalId = n.id;

                        jsTreeState.jsTreeDragAndDropSource = n;
                        jsTreeState.jsTreeDragAndDropTarget = p;
                        n.state.targetPurchasedActivityId = jsTreeState.jsTreeDragAndDropTarget.id;
                        n.state.targetLoadId = jsTreeState.jsTreeDragAndDropTarget.parent;
                        n.state.sourcePurchasedActivityId = n.parent;
                        n.state.sourceLoadId = jsTreeState.jsTreeDragAndDropSource.parents[1];
                        n.targetPurchasedActivityId = jsTreeState.jsTreeDragAndDropTarget.id;
                        hideDetailsWidget();
                        return true;

                    }
                }
                return false;
            },
            'themes': {
                'responsive': true,
                'stripes':true
            }
        },
        'sort': function (a, b) {
            return this.get_type(a) === this.get_type(b) ? (this.get_text(a) > this.get_text(b) ? 1 : -1) : (this.get_type(a) >= this.get_type(b) ? 1 : -1);
        },
        'contextmenu': {
            'items': function (node) {
                var tmp = $.jstree.defaults.contextmenu.items();
                delete tmp.create.action;
                delete tmp.rename.action;
                delete tmp.remove.action;
                delete tmp.ccp;
                tmp.create.label = "Move to new purchased activity";
                tmp.create.action = function (data) {
                    moveCustomerShipments();
                }

                tmp.rename.label = "Undo";
                tmp.rename.action = function (data) {
                    undoNodesMove(data);
                }

                tmp.remove.label = "Delete";
                tmp.remove.action = function (data) {
                    deleteNodes();
                }

                var nodeFull = $('#treeLoads').jstree('get_node', node.id);
                if (node.original.type == "purchasedactivities" || node.original.type == "loads" || nodeFull.originalId != undefined) {
                    delete tmp.create;
                }
                if (nodeFull.icon.indexOf("edit") < 0 && nodeFull.icon.indexOf("deleted") < 0) {
                    delete tmp.rename;
                }
                delete tmp.remove;
                return tmp;
            }
        },
        'types': {
            'loads': { 'icon': 'jstree-folder' },
            'purchasedactivities': { 'icon': 'jstree-folder' },
            'customershipments': { 'valid_children': [], 'icon': 'jstree-file' }
        },
        'plugins': ['state', 'dnd',  'types', 'contextmenu', 'unique']
    });
});


function showDetailsWidget(paReference) {
    $("form.purchasedActivity.personalize-form").formPersonalisation( {stateServiceUrl:"/load-planning/viewcontext.json"});
    if (paReference != undefined) {
        $("#pa-reference").val(paReference);
    }
    $("#detailsWidget").show();

}
function hideDetailsWidget() {
    $("#detailsWidget").hide();

}
function reloadTree() {
    jsTreeState.pageIndex=1;
    $('#treeLoads').jstree().refresh();
}
function isLoadStillChanged(selectedLoad) {
    var treeInstance = $('#treeLoads');
    var loadNode = treeInstance.jstree('get_json', selectedLoad);
    for (var i = 0; i < loadNode.children.length; i++) {
        if (loadNode.children[i].icon.indexOf("edit") > 0 || loadNode.children[i].icon.indexOf("deleted") > 0) {
            return true;
        }
    }
    return false;
}
function undoNodesMove(data) {
    var treeInstance = $('#treeLoads');
    var node = treeInstance.jstree('get_selected', true);
    if (node.length > 0) {

        var type = node[0].type;
        if (type == "loads") {
            var selectedNode = $("#treeLoads").jstree("get_selected", true);
            $("#treeLoads").jstree("refresh_node", selectedNode[0]);
            $('#treeLoads').jstree('set_icon', selectedNode[0], "jstree-folder");
        } else if (type == "purchasedactivities") {
            var treeInstance = $('#treeLoads');
            var node = $('#treeLoads').jstree("get_selected");
            var selectedPurchasedActivityNode = treeInstance.jstree('get_node', node[0]);
            var inst = $.jstree.reference(selectedPurchasedActivityNode.id);
            var customerShipmentsNodes = new Array();
            for (var i = 0; i < selectedPurchasedActivityNode.children.length; i++) {
                var customerShipmentNode = treeInstance.jstree('get_node', selectedPurchasedActivityNode.children[i]);
                if (isNodeModifed(customerShipmentNode)) {
                    delete customerShipmentNode.originalId;
                    customerShipmentNode.type = "customershipments";
                    customerShipmentsNodes.push(customerShipmentNode);
                }
            }
            if (customerShipmentsNodes.length > 0) {

                inst.move_node(customerShipmentsNodes, selectedPurchasedActivityNode.original.originalPurchasedActivityId, "last", function (new_node) {

                    treeInstance.jstree('set_icon', new_node.id, "jstree-file");
                    treeInstance.jstree('set_icon', selectedPurchasedActivityNode.id, "jstree-folder");
                    if (!isLoadStillChanged(selectedPurchasedActivityNode.parent)) {
                        treeInstance.jstree('set_icon', selectedPurchasedActivityNode.parent, "jstree-folder");
                    }
                    treeInstance.jstree('set_icon', selectedPurchasedActivityNode.original.originalPurchasedActivityId, "jstree-folder");

                    var originPurchasedActivityNode = treeInstance.jstree("get_node", treeInstance.jstree("get_node", customerShipmentsNodes[0]).parent);
                    if (!isLoadStillChanged(originPurchasedActivityNode.parent)) {
                        treeInstance.jstree('set_icon', originPurchasedActivityNode.parent, "jstree-folder");
                    }


                });

            }
        }

    }
}

function isNodeModifed(node) {
    if (node.icon.indexOf("moved") > 0 || node.icon.indexOf("edited") > 0 || node.icon.indexOf("deleted") > 0) {
        return true;
    } else {
        return false;
    }
}
function setNodesAsDeleted(nodesId, originalPurchasedActivityId, createdPurchasedActivityId, loadId) {
    var treeInstance = $("#treeLoads");
    treeInstance.jstree('set_icon', treeInstance.jstree('get_selected', true), "jstree-folder-deleted");

    $('#treeLoads').jstree('set_icon', loadId, "jstree-folder-edited");
    $('#treeLoads').jstree('set_icon', nodesId, "jstree-file-deleted");
}
function deleteNodes() {
    var selectedNodes = $('#treeLoads').jstree('get_selected', true);
    var type = selectedNodes[0].type;
    if (type == "customershipments") {
        $('#treeLoads').jstree('set_icon', selectedNodes, "jstree-file-deleted");
        $('#treeLoads').jstree('set_icon', selectedNodes[0].parent, "jstree-folder-edited");
        var purchasedActivityNode = $('#treeLoads').jstree('get_node', selectedNodes[0].parent);

        var loadNodeId = purchasedActivityNode.parent;
        setNodesAsDeleted(selectedNodes,loadNodeId);
    } else if (type == "purchasedactivities") {
        $('#treeLoads').jstree('set_icon', selectedNodes[0], "jstree-folder-deleted");
        var children = $('#treeLoads').jstree('get_node', selectedNodes[0].id).children;
        var purchasedActivityNode = selectedNodes[0];
        var loadNodeId = purchasedActivityNode.parent;
        setNodesAsDeleted(children, loadNodeId);
    }

    jsTreeState.activeLoad = null;
}

function moveCustomerShipments() {
    var selectedNodes = $('#treeLoads').jstree('get_selected', true);
    var concancenatedListOfCustomerShipments = "";
    for (var i = 0; i < selectedNodes.length; i++) {
        if (selectedNodes[i].type != "loads" && selectedNodes[i].type != "purchasedactivities") {
            concancenatedListOfCustomerShipments = concancenatedListOfCustomerShipments + " " + selectedNodes[i].text;
        } else {
            $('#treeLoads').jstree('deselect_node', selectedNodes[i]);

        }

    }
    setModalInputFieldValues();
    $("#selectedCustomerShipments").html(concancenatedListOfCustomerShipments);
    $("#modalMovePurchasedActivity").modal("show");
}
function setModalInputFieldValues() {
    var loadEtdDate = $("#etd").val();
    if (loadEtdDate != undefined || loadEtdDate != null) {
        $('#modalDate').datepicker('setDate', new Date($("#etd").val()));
    }
    setPendingReference()

}
function setPendingReference() {
    var dateString = $("#modalDate").datepicker("getDate");
    var date = new Date(dateString);
    date.setDate(date.getDate() + 1);
    $("#reference").val(date.toJSON() + "_" + $("#supplier").val() + "_" + $("#senderCountryCode").val());
}
/**
 * Keeps track of Purchased Activities that has been changed
 **/

function createPurchasedActivityAndAssignCustomerShipments() {
    var purchasedActivityId = $('#treeLoads').jstree('get_selected', true)[0].parent;
    var purchasedActivity = $('#treeLoads').jstree('get_json', purchasedActivityId);
    var purchasedActivityNode = $('#treeLoads').jstree('get_node', purchasedActivityId);
    var loadRootId = $('#treeLoads').jstree('get_selected', true)[0].parents[1];
    var loadRoot = $('#treeLoads').jstree('get_json', loadRootId);
    var inst = $.jstree.reference(loadRootId);
    //Create new purchased activity
    purchasedActivity.children = [];
    purchasedActivity.icon = "jstree-folder-edited";
    purchasedActivity.state.opened = true;
    purchasedActivity.originalId = purchasedActivity.id;

    var customerShipments = $('#treeLoads').jstree('get_selected', true);
    for (var i = 0; i < customerShipments.length; i++) {
        customerShipments[i].icon = "jstree-file-edited";
        customerShipments[i].originalPurchasedActivityId = purchasedActivity.originalId;
        customerShipments[i].originalId = customerShipments[i].id;
        customerShipments[i].original = customerShipments[i].original;
    }

    purchasedActivity.id = "cirrusLoadTree-" + purchasedActivity.originalId + "-" + new Date().getUTCMilliseconds();
    purchasedActivity.text = $("#reference").val();
    purchasedActivity.original = purchasedActivityNode.original;
    purchasedActivity.type = "purchasedactivities";

    inst.create_node(loadRootId, purchasedActivity, "last", function (new_node) {

    });
    //Move selected customer shipments to created purchased activity
    inst.move_node(customerShipments, purchasedActivity.id, "last", function (new_node) {
        $("#modalMovePurchasedActivity").modal("hide");
        $('#treeLoads').jstree('deselect_all', false);
    });
    jsTreeState.changedPurchasedActivities[purchasedActivityId] = true;

    setNodesAsChanged(customerShipments, purchasedActivity.id, purchasedActivityId, purchasedActivityNode.parent);
}
function setNodesAsChanged(nodesId, originalPurchasedActivityId, createdPurchasedActivityId, loadId) {
    $('#treeLoads').jstree('set_icon', createdPurchasedActivityId, "jstree-folder-edited");
    $('#treeLoads').jstree('set_icon', originalPurchasedActivityId, "jstree-folder-edited");
    $('#treeLoads').jstree('set_icon', loadId, "jstree-folder-edited");
    for (var i = 0; i < nodesId.length; i++) {
        $('#treeLoads').jstree('set_icon', nodesId[i].id, "jstree-file-edited");
    }
}
function refreshDetails(loadid, globalPurchaseActivitityId) {
    var ids = getOriginalPurchasedActivityIds(globalPurchaseActivitityId);
    if (ids != undefined && ids != null && ids.id.length > 0) {
        $('.contentDetail').load('/load-planning/cirrus/v1/loadplanning/viewport/details?loadId=' + loadid, ids, function () {
            showDetailsWidget();
        });
    } else {
        showDetailsWidget();
    }

}
function getOriginalPurchasedActivityIds(purchasedActivityIds) {
    var ids = new Object();
    var idsArray = new Array();
    for (var i = 0; i < purchasedActivityIds.length; i++) {
        if (purchasedActivityIds[i].indexOf("cirrusLoadTree-") < 0) {
            idsArray.push(purchasedActivityIds[i]);
        }
    }
    ids.id = idsArray;
    return ids;
}
/**
 * Used to get load details and its children after it has been moved to
 * a new purchased activity.
 */
function refreshMovedDetails(purchasedActitivityId, loadId, type) {

    var customerShipments;
    var ids = new Object();
    var idsArray = new Array();
    var parentNodeId;
    var purchasedActivityNode;
    var selectedPAReference;
    if (type == "customershipments") {
        parentNodeId = $('#treeLoads').jstree('get_selected', true)[0].parents[0];
        purchasedActivityNode = $("#treeLoads").jstree("get_json", parentNodeId);
        selectedPAReference = purchasedActivityNode.text;
        customerShipments = purchasedActivityNode.children;
        for (var i = 0; i < customerShipments.length; i++) {
            if (customerShipments[i].icon != "jstree-file-deleted") {
                idsArray.push(customerShipments[i].id);
            }


        }

        ids.id = idsArray;

    } else if (type == "purchasedactivities") {
        purchasedActivityNode = $('#treeLoads').jstree('get_selected', true);
        selectedPAReference = purchasedActivityNode[0].text;
        purchasedActivityNode = $("#treeLoads").jstree("get_json", purchasedActivityNode[0].id);
        customerShipments = purchasedActivityNode.children;
        for (var i = 0; i < customerShipments.length; i++) {
            if (customerShipments[i].icon != "jstree-file-deleted") {
                idsArray.push(customerShipments[i].id);
            }
        }
        ids.id = idsArray;
    }
    if (idsArray.length > 0) {

        purchasedActivityNode = $('#treeLoads').jstree('get_node', purchasedActitivityId);
        if (purchasedActitivityId.indexOf("cirrusLoadTree-") >= 0) {
            purchasedActitivityId = purchasedActivityNode.original.originalPurchasedActivityId;
        }

        $('.contentDetail').load('/load-planning/cirrus/v1/loadplanning/viewport/customershipments/pending?purchasedActivityId=' + purchasedActitivityId + '&loadId=' + loadId, ids, function () {
            showDetailsWidget(selectedPAReference);
        });
    } else {
        idsArray.push(-1);
        ids.id = idsArray;
        $('.contentDetail').load('/load-planning/cirrus/v1/loadplanning/viewport/customershipments/pending?purchasedActivityId=' + purchasedActitivityId + '&loadId=' + loadId, ids, function () {
            showDetailsWidget(selectedPAReference);
        });
    }

}


function filterTreeNodes(filterString) {
    $('#treeLoads').jstree("search", filterString, false);
}
function undoAll() {
    $('#treeLoads').jstree("refresh");
}
function reset() {
    hideDetailsWidget();
    $('#treeLoads').jstree("clear_state");
    $('#treeLoads').jstree("close_all");
    $('#treeLoads').jstree("deselect_all");
    reloadTree();
}

function saveChanges() {
    $("#modalLayer").css("visibility", "");
    $.ajax({
        type: "post",
        dataType: 'json',
        url: "/load-planning/cirrus/v1/loadplanning/viewport/move/customershipments.json",
        contentType: "application/json",
        data: JSON.stringify(getChanges()), //json object or array of json objects
        success: function (load) {
            var length = load.length;
            var treeInstance = $('#treeLoads');
            for (var i = 0; i < length; i++) {
                treeInstance.jstree('set_icon', load[i].id, "jstree-folder");
                treeInstance.jstree('refresh_node', load[i].id);
            }
            if (length > 0) {
                treeInstance.jstree('select_node', load[0].id);
            }
            setTimeout(function () {
                $("#modalLayer").css("visibility", "hidden");
            }, 200)
        }
    });
}
function getChanges() {
    var loads = new Array();
    var nodes = $("#treeLoads").jstree("get_json", "#", false);
    nodes.filter(function (index, val) {
        //Find all changed nodes
        if (index.icon.indexOf("edited") > 0 || index.icon.indexOf("deleted") > 0 || index.icon.indexOf("moved") > 0) {
            var loadChangeSet = new Object();
            loadChangeSet.id = Number(index.id);

            loadChangeSet.name = index.text;
            loadChangeSet.purchasedActivities = new Array();

            $(index.children).each(function (pa, value) {

                var purchasedActivity = new Object();
                purchasedActivity.targetLoadId = null;
                purchasedActivity.targetPurchasedActivityId = null;
                purchasedActivity.targetLoadId = null;
                purchasedActivity.name = value.text;
                purchasedActivity.loadId = loadChangeSet.id;
                purchasedActivity.customerShipments = new Array();
                if (value.id.indexOf("cirrusLoadTree-") < 0) {
                    purchasedActivity.id = Number(value.id);
                } else {
                    purchasedActivity.id = Number(value.id.split("-")[1]);
                    purchasedActivity.pendingId = value.id;
                }


                $(value.children).each(function (pa2, value2) {
                    if (value2.icon.indexOf("edited") > 0 || value2.icon.indexOf("deleted") > 0 || value2.icon.indexOf("moved") > 0) {
                        if (value2.icon.indexOf("moved") > 0) {
                            purchasedActivity.loadId = Number(value2.state.sourceLoadId);
                            purchasedActivity.targetLoadId = Number(value2.state.targetLoadId);
                            purchasedActivity.targetPurchasedActivityId = Number(value2.state.targetPurchasedActivityId);
                            purchasedActivity.id = Number(value2.state.sourcePurchasedActivityId);
                        }
                        var customerShipment = new Object();
                        customerShipment.id = value2.id;
                        customerShipment.name = value2.text;
                        purchasedActivity.customerShipments.push(customerShipment);


                    }

                });
                if (purchasedActivity.customerShipments.length > 0) {
                    loadChangeSet.purchasedActivities.push(purchasedActivity);
                }

            });
            if (loadChangeSet.purchasedActivities.length != 0) {
                loads.push(loadChangeSet);
            }
        }

    });

    return loads;
}

function hidePaginationBar(){
    $("#pagingPage").hide();
    $("#pagingSummary").hide();
    $(".tree-pagination").hide();

}
function populateGoToPageDropdown(){
    $('#pageSelection option').remove()
    for(var i=0;i<jsTreeState.totalPages;i++){
        $('#pageSelection').append("<option>"+(i+1)+"</option>");
    }
}

function updatePaginationBar(begin, end, totalRecords, currentPage, totalPages){

    jsTreeState.totalPages=totalPages;
    if(totalPages>1) {
        $("#pagingPage").show();
        $(".tree-pagination").show();

        $("#pagingPage").html("Showing page " + currentPage + " of " + totalPages);
    }else{
        hidePaginationBar();
    }

}
function goToPage(){
    $('#pagingPage').popover("hide");
    jsTreeState.pageIndex=new Number($("#pageSelection").val());
    $('#treeLoads').jstree().refresh();

}
function loadNextPage(){
    jsTreeState.pageIndex=jsTreeState.pageIndex+1;
    if(jsTreeState.pageIndex>jsTreeState.totalPages){
        jsTreeState.pageIndex = jsTreeState.totalPages;
    }else{
        $('#treeLoads').jstree().refresh();

    }

}
function loadPreviousPage(){
    jsTreeState.pageIndex=jsTreeState.pageIndex-1;
    if(jsTreeState.pageIndex<1){
        jsTreeState.pageIndex=1;
    }else{
        $('#treeLoads').jstree().refresh();
    }
}

function setReferenceType(type) {
    jsLoadSearch.type = type;
    var typeString =$("#" + type).text();
    $("#referenceType").val(type);
    $("#btnReferenceType").text( typeString+ " ");
    $("#tooltipType").text( typeString+ " ");


}
function distanceUnloadingSequenceEdited(e, json, data, tableId){
    $("input[name=distance]",$(tableId).parentsUntil(".purchasedActivity")).val(json.row.distance);
}
$(document).on("dnd_stop.vakata", function(data, element, helper, event) {
    if(jsTreeState.jsTreeDragAndDropTarget!=null && jsTreeState.jsTreeDragAndDropTarget.id!=null) {
        refreshDetails(jsTreeState.activeLoad, [jsTreeState.jsTreeDragAndDropTarget.id]);
    }
});
