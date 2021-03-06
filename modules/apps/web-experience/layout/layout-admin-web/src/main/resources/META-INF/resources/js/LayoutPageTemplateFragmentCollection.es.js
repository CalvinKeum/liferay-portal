import Component from 'metal-component';
import {Config} from 'metal-state';
import Soy from 'metal-soy';

import templates from './LayoutPageTemplateFragmentCollection.soy';

/**
 * LayoutPageTemplateFragmentCollection
 */
class LayoutPageTemplateFragmentCollection extends Component {
	/**
	 * Callback that is executed when a fragment entry is clicked.
	 * It propagates a collectionEntryClick event with the fragment information.
	 * @param {Event} event
	 * @private
	 */
	_handleEntryClick(event) {
		const fragmentEntryId = event.delegateTarget.dataset.fragmentEntryId;
		const fragmentName = this.fragmentCollection.fragmentEntries.find(
			entry => entry.fragmentEntryId === fragmentEntryId
		).name;

		this.emit('collectionEntryClick', {
			fragmentEntryId,
			fragmentName,
		});
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */
LayoutPageTemplateFragmentCollection.STATE = {
	/**
	 * Available entries that can be dragged inside the existing Page Template,
	 * organized by fragment categories.
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateFragmentCollection
	 * @type {!Array<object>}
	 */
	fragmentCollection: Config.shapeOf({
		fragmentCollectionId: Config.string().required(),
		name: Config.string().required(),
		fragmentEntries: Config.arrayOf(
			Config.shapeOf({
				fragmentEntryId: Config.string().required(),
				name: Config.string().required(),
			}).required()
		).required(),
	}),

	/**
	 * Portlet namespace needed for prefixing form inputs
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateFragmentCollection
	 * @type {!string}
	 */
	portletNamespace: Config.string().required(),

	/**
	 * Path of the available icons.
	 * @default undefined
	 * @instance
	 * @memberOf LayoutPageTemplateFragmentCollection
	 * @type {!string}
	 */
	spritemap: Config.string().required(),
};

Soy.register(LayoutPageTemplateFragmentCollection, templates);

export {LayoutPageTemplateFragmentCollection};
export default LayoutPageTemplateFragmentCollection;
