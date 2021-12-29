package org.superbapps.utils.vaadin.Trees;

import org.superbapps.utils.vaadin.Exceptions.CustomTreeNodesEmptyException;
import org.superbapps.utils.vaadin.Forms.Form_CRUD2;
import org.superbapps.utils.vaadin.Tables.IRefreshVisualContainer;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * <b>CustomObjectTree</b> class has root nodes of the type T.</p>
 * For every root node, there is a list with it's own sub-nodes.
 */
public abstract class CustomObjectTree<T> extends CustomTree<T> implements IRefreshVisualContainer {

	protected Date dateFrom;
	protected Date dateTo;

	protected Form_CRUD2<?> crudForm;
	protected Object[]      optionalParams;

	public CustomObjectTree(String caption, List<T> rootNodes) throws CustomTreeNodesEmptyException, NullPointerException {
		super( caption, rootNodes );
		recreateAllSubNodes();
	}

	public CustomObjectTree(String caption, List<T> rootNodes, boolean expandRootNodes) throws CustomTreeNodesEmptyException, NullPointerException {
		super( caption, rootNodes, expandRootNodes );
		recreateAllSubNodes();
	}

	public CustomObjectTree(List<T> rootNodes, boolean expandRootNodes) throws CustomTreeNodesEmptyException, NullPointerException {
		super( "", rootNodes, expandRootNodes );
		recreateAllSubNodes();
	}

	public CustomObjectTree(List<T> rootNodes, boolean expandRootNodes, Object... optionalParams) throws CustomTreeNodesEmptyException, NullPointerException {
		super( "", rootNodes, expandRootNodes );
		this.optionalParams = optionalParams;
		recreateAllSubNodes();
	}

	//<editor-fold desc="Recreate All Sub Nodes">
	protected final void recreateAllSubNodes() {
		//<editor-fold defaultstate="collapsed" desc="prastara verzija">
        /*
        if (!items.getContainerPropertyIds().isEmpty()) {
        items.getContainerPropertyIds().stream().forEach((rootNode) -> {
        createSubNodes((T) rootNode);
        });
        }
         */
		//</editor-fold>

		if (!elements.isEmpty()) {
			elements.forEach( this::createSubNodes );
		}
	}
	//</editor-fold>

	//<editor-fold defaultstate="collapsed" desc="createSubNodes">

	/**
	 * For all defined root nodes, dynamically create<br>
	 * all child nodes for each root node !<br>
	 * How sub-nodes list is generated, is defined in the extended class<br>
	 *
	 * @param t This is the one of the list of root nodes,<br>
	 *          for which we want to create it's own list of sub-nodes.
	 */
	protected abstract void createSubNodes(T t);
	//</editor-fold>

	//<editor-fold defaultstate="collapsed" desc="createChildNodesForTheRoot">
	/**
	 * Create this tree with <b>single</b> type T root node,<br>
	 * with it's sub-nodes list "subList".
	 *
	 * @param root               root node.
	 * @param rootChildListNodes root's nodes sub-list.
	 * @param expandRootNodes    expand root nodes ?
	 */
	protected void createChildNodesForTheRoot(T root, List<?> rootChildListNodes, boolean expandRootNodes) {
		super.setNodeItems( root, rootChildListNodes, expandRootNodes );
	}
	//</editor-fold>

	//<editor-fold defaultstate="collapsed" desc="refreshVisualContainer">
	@Override
	public void refreshVisualContainer() {
		recreateAllSubNodes();
	}
	//</editor-fold>

}
