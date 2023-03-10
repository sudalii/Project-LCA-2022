package org.openlca.app.viewers;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.openlca.core.model.RefEntity;

/**
 * Filters instances of {@link IModelComponent} by their name.
 */
public class BaseNameFilter extends ViewerFilter {

	/** The filter attribute. */
	protected String filter = null;

	/**
	 * Returns true when the content matches the filter attribute, otherwise
	 * false.
	 * 
	 * @param filter
	 *            the filter attribute
	 * @param content
	 *            the content to be selected if the filter attribute matches
	 * @return true when the content matches the filter attribute, otherwise
	 *         false
	 */
	protected boolean select(final String filter, final String content) {
		boolean b = false;
		if (filter == null && content != null) {
			b = true; // no filter
		} else if (content == null) {
			b = false;
		} else if (filter != null && filter.charAt(0) == '*') {
			final String f = filter.substring(1);
			b = content.toLowerCase().contains(f.toLowerCase());
		} else if (filter != null) {
			b = content.toLowerCase().startsWith(filter.toLowerCase());
		}
		return b;
	}

	/**
	 * Get the filter attribute.
	 * 
	 * @return the filter attribute.
	 */
	public String getFilter() {
		return filter;
	}

	@Override
	public boolean select(final Viewer viewer, final Object parentElement,
			final Object element) {
		String content = null;
		if (element instanceof RefEntity) {
			content = ((RefEntity) element).name;
		}
		return select(filter, content);
	}

	/**
	 * Set the filter attribute.
	 * 
	 * @param filter
	 *            the filter attribute
	 */
	public void setFilter(final String filter) {
		this.filter = filter;
	}

}
