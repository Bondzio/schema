function applyModalPanelEffect(panelId, effectFunc, params, hide) {
    if (panelId &amp;&amp; effectFunc) {
        var modalPanel = $(panelId);
        if (modalPanel &amp;&amp; modalPanel.component) {
            var component = modalPanel.component;
            var div = component.getSizedElement();
            if (hide) {
                Element.hide(div);
            }
            effectFunc.call(this, Object.extend({targetId : div.id}, params || {}));
        }
    }
}

function showModalPanelWithEffect(panelId, showEffect, params) {
    applyModalPanelEffect(panelId, showEffect, params, true);
}

function hideModalPanelWithEffect(panelId, hideEffect, params) {
    var _params = params;
    _params['afterFinish'] = function() {
        Richfaces.hideModalPanel(panelId)
    };
    applyModalPanelEffect(panelId, hideEffect, params, false);
}
