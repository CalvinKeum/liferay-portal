/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portlet.asset.service.base;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.asset.kernel.service.persistence.AssetCategoryFinder;
import com.liferay.asset.kernel.service.persistence.AssetCategoryPersistence;
import com.liferay.asset.kernel.service.persistence.AssetEntryFinder;
import com.liferay.asset.kernel.service.persistence.AssetEntryPersistence;
import com.liferay.asset.kernel.service.persistence.AssetLinkFinder;
import com.liferay.asset.kernel.service.persistence.AssetLinkPersistence;
import com.liferay.asset.kernel.service.persistence.AssetTagFinder;
import com.liferay.asset.kernel.service.persistence.AssetTagPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.GroupFinder;
import com.liferay.portal.kernel.service.persistence.GroupPersistence;
import com.liferay.portal.kernel.service.persistence.UserFinder;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.social.kernel.service.persistence.SocialActivityCounterFinder;
import com.liferay.social.kernel.service.persistence.SocialActivityCounterPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the asset entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portlet.asset.service.impl.AssetEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.asset.service.impl.AssetEntryLocalServiceImpl
 * @generated
 */
public abstract class AssetEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements AssetEntryLocalService, IdentifiableOSGiService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>AssetEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the asset entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntry the asset entry
	 * @return the asset entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetEntry addAssetEntry(AssetEntry assetEntry) {
		assetEntry.setNew(true);

		return assetEntryPersistence.update(assetEntry);
	}

	/**
	 * Creates a new asset entry with the primary key. Does not add the asset entry to the database.
	 *
	 * @param entryId the primary key for the new asset entry
	 * @return the new asset entry
	 */
	@Override
	@Transactional(enabled = false)
	public AssetEntry createAssetEntry(long entryId) {
		return assetEntryPersistence.create(entryId);
	}

	/**
	 * Deletes the asset entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param entryId the primary key of the asset entry
	 * @return the asset entry that was removed
	 * @throws PortalException if a asset entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AssetEntry deleteAssetEntry(long entryId) throws PortalException {
		return assetEntryPersistence.remove(entryId);
	}

	/**
	 * Deletes the asset entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntry the asset entry
	 * @return the asset entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public AssetEntry deleteAssetEntry(AssetEntry assetEntry) {
		return assetEntryPersistence.remove(assetEntry);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			AssetEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return assetEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.asset.model.impl.AssetEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return assetEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.asset.model.impl.AssetEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return assetEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return assetEntryPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return assetEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public AssetEntry fetchAssetEntry(long entryId) {
		return assetEntryPersistence.fetchByPrimaryKey(entryId);
	}

	/**
	 * Returns the asset entry with the primary key.
	 *
	 * @param entryId the primary key of the asset entry
	 * @return the asset entry
	 * @throws PortalException if a asset entry with the primary key could not be found
	 */
	@Override
	public AssetEntry getAssetEntry(long entryId) throws PortalException {
		return assetEntryPersistence.findByPrimaryKey(entryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(assetEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AssetEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("entryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			assetEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(AssetEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("entryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(assetEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(AssetEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("entryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return assetEntryLocalService.deleteAssetEntry(
			(AssetEntry)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return assetEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the asset entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portlet.asset.model.impl.AssetEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of asset entries
	 * @param end the upper bound of the range of asset entries (not inclusive)
	 * @return the range of asset entries
	 */
	@Override
	public List<AssetEntry> getAssetEntries(int start, int end) {
		return assetEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of asset entries.
	 *
	 * @return the number of asset entries
	 */
	@Override
	public int getAssetEntriesCount() {
		return assetEntryPersistence.countAll();
	}

	/**
	 * Updates the asset entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param assetEntry the asset entry
	 * @return the asset entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public AssetEntry updateAssetEntry(AssetEntry assetEntry) {
		return assetEntryPersistence.update(assetEntry);
	}

	/**
	 */
	@Override
	public void addAssetCategoryAssetEntry(long categoryId, long entryId) {
		assetCategoryPersistence.addAssetEntry(categoryId, entryId);
	}

	/**
	 */
	@Override
	public void addAssetCategoryAssetEntry(
		long categoryId, AssetEntry assetEntry) {

		assetCategoryPersistence.addAssetEntry(categoryId, assetEntry);
	}

	/**
	 */
	@Override
	public void addAssetCategoryAssetEntries(long categoryId, long[] entryIds) {
		assetCategoryPersistence.addAssetEntries(categoryId, entryIds);
	}

	/**
	 */
	@Override
	public void addAssetCategoryAssetEntries(
		long categoryId, List<AssetEntry> assetEntries) {

		assetCategoryPersistence.addAssetEntries(categoryId, assetEntries);
	}

	/**
	 */
	@Override
	public void clearAssetCategoryAssetEntries(long categoryId) {
		assetCategoryPersistence.clearAssetEntries(categoryId);
	}

	/**
	 */
	@Override
	public void deleteAssetCategoryAssetEntry(long categoryId, long entryId) {
		assetCategoryPersistence.removeAssetEntry(categoryId, entryId);
	}

	/**
	 */
	@Override
	public void deleteAssetCategoryAssetEntry(
		long categoryId, AssetEntry assetEntry) {

		assetCategoryPersistence.removeAssetEntry(categoryId, assetEntry);
	}

	/**
	 */
	@Override
	public void deleteAssetCategoryAssetEntries(
		long categoryId, long[] entryIds) {

		assetCategoryPersistence.removeAssetEntries(categoryId, entryIds);
	}

	/**
	 */
	@Override
	public void deleteAssetCategoryAssetEntries(
		long categoryId, List<AssetEntry> assetEntries) {

		assetCategoryPersistence.removeAssetEntries(categoryId, assetEntries);
	}

	/**
	 * Returns the categoryIds of the asset categories associated with the asset entry.
	 *
	 * @param entryId the entryId of the asset entry
	 * @return long[] the categoryIds of asset categories associated with the asset entry
	 */
	@Override
	public long[] getAssetCategoryPrimaryKeys(long entryId) {
		return assetEntryPersistence.getAssetCategoryPrimaryKeys(entryId);
	}

	/**
	 */
	@Override
	public List<AssetEntry> getAssetCategoryAssetEntries(long categoryId) {
		return assetCategoryPersistence.getAssetEntries(categoryId);
	}

	/**
	 */
	@Override
	public List<AssetEntry> getAssetCategoryAssetEntries(
		long categoryId, int start, int end) {

		return assetCategoryPersistence.getAssetEntries(categoryId, start, end);
	}

	/**
	 */
	@Override
	public List<AssetEntry> getAssetCategoryAssetEntries(
		long categoryId, int start, int end,
		OrderByComparator<AssetEntry> orderByComparator) {

		return assetCategoryPersistence.getAssetEntries(
			categoryId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getAssetCategoryAssetEntriesCount(long categoryId) {
		return assetCategoryPersistence.getAssetEntriesSize(categoryId);
	}

	/**
	 */
	@Override
	public boolean hasAssetCategoryAssetEntry(long categoryId, long entryId) {
		return assetCategoryPersistence.containsAssetEntry(categoryId, entryId);
	}

	/**
	 */
	@Override
	public boolean hasAssetCategoryAssetEntries(long categoryId) {
		return assetCategoryPersistence.containsAssetEntries(categoryId);
	}

	/**
	 */
	@Override
	public void setAssetCategoryAssetEntries(long categoryId, long[] entryIds) {
		assetCategoryPersistence.setAssetEntries(categoryId, entryIds);
	}

	/**
	 */
	@Override
	public void addAssetTagAssetEntry(long tagId, long entryId) {
		assetTagPersistence.addAssetEntry(tagId, entryId);
	}

	/**
	 */
	@Override
	public void addAssetTagAssetEntry(long tagId, AssetEntry assetEntry) {
		assetTagPersistence.addAssetEntry(tagId, assetEntry);
	}

	/**
	 */
	@Override
	public void addAssetTagAssetEntries(long tagId, long[] entryIds) {
		assetTagPersistence.addAssetEntries(tagId, entryIds);
	}

	/**
	 */
	@Override
	public void addAssetTagAssetEntries(
		long tagId, List<AssetEntry> assetEntries) {

		assetTagPersistence.addAssetEntries(tagId, assetEntries);
	}

	/**
	 */
	@Override
	public void clearAssetTagAssetEntries(long tagId) {
		assetTagPersistence.clearAssetEntries(tagId);
	}

	/**
	 */
	@Override
	public void deleteAssetTagAssetEntry(long tagId, long entryId) {
		assetTagPersistence.removeAssetEntry(tagId, entryId);
	}

	/**
	 */
	@Override
	public void deleteAssetTagAssetEntry(long tagId, AssetEntry assetEntry) {
		assetTagPersistence.removeAssetEntry(tagId, assetEntry);
	}

	/**
	 */
	@Override
	public void deleteAssetTagAssetEntries(long tagId, long[] entryIds) {
		assetTagPersistence.removeAssetEntries(tagId, entryIds);
	}

	/**
	 */
	@Override
	public void deleteAssetTagAssetEntries(
		long tagId, List<AssetEntry> assetEntries) {

		assetTagPersistence.removeAssetEntries(tagId, assetEntries);
	}

	/**
	 * Returns the tagIds of the asset tags associated with the asset entry.
	 *
	 * @param entryId the entryId of the asset entry
	 * @return long[] the tagIds of asset tags associated with the asset entry
	 */
	@Override
	public long[] getAssetTagPrimaryKeys(long entryId) {
		return assetEntryPersistence.getAssetTagPrimaryKeys(entryId);
	}

	/**
	 */
	@Override
	public List<AssetEntry> getAssetTagAssetEntries(long tagId) {
		return assetTagPersistence.getAssetEntries(tagId);
	}

	/**
	 */
	@Override
	public List<AssetEntry> getAssetTagAssetEntries(
		long tagId, int start, int end) {

		return assetTagPersistence.getAssetEntries(tagId, start, end);
	}

	/**
	 */
	@Override
	public List<AssetEntry> getAssetTagAssetEntries(
		long tagId, int start, int end,
		OrderByComparator<AssetEntry> orderByComparator) {

		return assetTagPersistence.getAssetEntries(
			tagId, start, end, orderByComparator);
	}

	/**
	 */
	@Override
	public int getAssetTagAssetEntriesCount(long tagId) {
		return assetTagPersistence.getAssetEntriesSize(tagId);
	}

	/**
	 */
	@Override
	public boolean hasAssetTagAssetEntry(long tagId, long entryId) {
		return assetTagPersistence.containsAssetEntry(tagId, entryId);
	}

	/**
	 */
	@Override
	public boolean hasAssetTagAssetEntries(long tagId) {
		return assetTagPersistence.containsAssetEntries(tagId);
	}

	/**
	 */
	@Override
	public void setAssetTagAssetEntries(long tagId, long[] entryIds) {
		assetTagPersistence.setAssetEntries(tagId, entryIds);
	}

	/**
	 * Returns the asset entry local service.
	 *
	 * @return the asset entry local service
	 */
	public AssetEntryLocalService getAssetEntryLocalService() {
		return assetEntryLocalService;
	}

	/**
	 * Sets the asset entry local service.
	 *
	 * @param assetEntryLocalService the asset entry local service
	 */
	public void setAssetEntryLocalService(
		AssetEntryLocalService assetEntryLocalService) {

		this.assetEntryLocalService = assetEntryLocalService;
	}

	/**
	 * Returns the asset entry persistence.
	 *
	 * @return the asset entry persistence
	 */
	public AssetEntryPersistence getAssetEntryPersistence() {
		return assetEntryPersistence;
	}

	/**
	 * Sets the asset entry persistence.
	 *
	 * @param assetEntryPersistence the asset entry persistence
	 */
	public void setAssetEntryPersistence(
		AssetEntryPersistence assetEntryPersistence) {

		this.assetEntryPersistence = assetEntryPersistence;
	}

	/**
	 * Returns the asset entry finder.
	 *
	 * @return the asset entry finder
	 */
	public AssetEntryFinder getAssetEntryFinder() {
		return assetEntryFinder;
	}

	/**
	 * Sets the asset entry finder.
	 *
	 * @param assetEntryFinder the asset entry finder
	 */
	public void setAssetEntryFinder(AssetEntryFinder assetEntryFinder) {
		this.assetEntryFinder = assetEntryFinder;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public com.liferay.portal.kernel.service.GroupLocalService
		getGroupLocalService() {

		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(
		com.liferay.portal.kernel.service.GroupLocalService groupLocalService) {

		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Returns the group finder.
	 *
	 * @return the group finder
	 */
	public GroupFinder getGroupFinder() {
		return groupFinder;
	}

	/**
	 * Sets the group finder.
	 *
	 * @param groupFinder the group finder
	 */
	public void setGroupFinder(GroupFinder groupFinder) {
		this.groupFinder = groupFinder;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	/**
	 * Returns the asset category local service.
	 *
	 * @return the asset category local service
	 */
	public com.liferay.asset.kernel.service.AssetCategoryLocalService
		getAssetCategoryLocalService() {

		return assetCategoryLocalService;
	}

	/**
	 * Sets the asset category local service.
	 *
	 * @param assetCategoryLocalService the asset category local service
	 */
	public void setAssetCategoryLocalService(
		com.liferay.asset.kernel.service.AssetCategoryLocalService
			assetCategoryLocalService) {

		this.assetCategoryLocalService = assetCategoryLocalService;
	}

	/**
	 * Returns the asset category persistence.
	 *
	 * @return the asset category persistence
	 */
	public AssetCategoryPersistence getAssetCategoryPersistence() {
		return assetCategoryPersistence;
	}

	/**
	 * Sets the asset category persistence.
	 *
	 * @param assetCategoryPersistence the asset category persistence
	 */
	public void setAssetCategoryPersistence(
		AssetCategoryPersistence assetCategoryPersistence) {

		this.assetCategoryPersistence = assetCategoryPersistence;
	}

	/**
	 * Returns the asset category finder.
	 *
	 * @return the asset category finder
	 */
	public AssetCategoryFinder getAssetCategoryFinder() {
		return assetCategoryFinder;
	}

	/**
	 * Sets the asset category finder.
	 *
	 * @param assetCategoryFinder the asset category finder
	 */
	public void setAssetCategoryFinder(
		AssetCategoryFinder assetCategoryFinder) {

		this.assetCategoryFinder = assetCategoryFinder;
	}

	/**
	 * Returns the social activity counter local service.
	 *
	 * @return the social activity counter local service
	 */
	public com.liferay.social.kernel.service.SocialActivityCounterLocalService
		getSocialActivityCounterLocalService() {

		return socialActivityCounterLocalService;
	}

	/**
	 * Sets the social activity counter local service.
	 *
	 * @param socialActivityCounterLocalService the social activity counter local service
	 */
	public void setSocialActivityCounterLocalService(
		com.liferay.social.kernel.service.SocialActivityCounterLocalService
			socialActivityCounterLocalService) {

		this.socialActivityCounterLocalService =
			socialActivityCounterLocalService;
	}

	/**
	 * Returns the social activity counter persistence.
	 *
	 * @return the social activity counter persistence
	 */
	public SocialActivityCounterPersistence
		getSocialActivityCounterPersistence() {

		return socialActivityCounterPersistence;
	}

	/**
	 * Sets the social activity counter persistence.
	 *
	 * @param socialActivityCounterPersistence the social activity counter persistence
	 */
	public void setSocialActivityCounterPersistence(
		SocialActivityCounterPersistence socialActivityCounterPersistence) {

		this.socialActivityCounterPersistence =
			socialActivityCounterPersistence;
	}

	/**
	 * Returns the social activity counter finder.
	 *
	 * @return the social activity counter finder
	 */
	public SocialActivityCounterFinder getSocialActivityCounterFinder() {
		return socialActivityCounterFinder;
	}

	/**
	 * Sets the social activity counter finder.
	 *
	 * @param socialActivityCounterFinder the social activity counter finder
	 */
	public void setSocialActivityCounterFinder(
		SocialActivityCounterFinder socialActivityCounterFinder) {

		this.socialActivityCounterFinder = socialActivityCounterFinder;
	}

	/**
	 * Returns the asset link local service.
	 *
	 * @return the asset link local service
	 */
	public com.liferay.asset.kernel.service.AssetLinkLocalService
		getAssetLinkLocalService() {

		return assetLinkLocalService;
	}

	/**
	 * Sets the asset link local service.
	 *
	 * @param assetLinkLocalService the asset link local service
	 */
	public void setAssetLinkLocalService(
		com.liferay.asset.kernel.service.AssetLinkLocalService
			assetLinkLocalService) {

		this.assetLinkLocalService = assetLinkLocalService;
	}

	/**
	 * Returns the asset link persistence.
	 *
	 * @return the asset link persistence
	 */
	public AssetLinkPersistence getAssetLinkPersistence() {
		return assetLinkPersistence;
	}

	/**
	 * Sets the asset link persistence.
	 *
	 * @param assetLinkPersistence the asset link persistence
	 */
	public void setAssetLinkPersistence(
		AssetLinkPersistence assetLinkPersistence) {

		this.assetLinkPersistence = assetLinkPersistence;
	}

	/**
	 * Returns the asset link finder.
	 *
	 * @return the asset link finder
	 */
	public AssetLinkFinder getAssetLinkFinder() {
		return assetLinkFinder;
	}

	/**
	 * Sets the asset link finder.
	 *
	 * @param assetLinkFinder the asset link finder
	 */
	public void setAssetLinkFinder(AssetLinkFinder assetLinkFinder) {
		this.assetLinkFinder = assetLinkFinder;
	}

	/**
	 * Returns the asset tag local service.
	 *
	 * @return the asset tag local service
	 */
	public com.liferay.asset.kernel.service.AssetTagLocalService
		getAssetTagLocalService() {

		return assetTagLocalService;
	}

	/**
	 * Sets the asset tag local service.
	 *
	 * @param assetTagLocalService the asset tag local service
	 */
	public void setAssetTagLocalService(
		com.liferay.asset.kernel.service.AssetTagLocalService
			assetTagLocalService) {

		this.assetTagLocalService = assetTagLocalService;
	}

	/**
	 * Returns the asset tag persistence.
	 *
	 * @return the asset tag persistence
	 */
	public AssetTagPersistence getAssetTagPersistence() {
		return assetTagPersistence;
	}

	/**
	 * Sets the asset tag persistence.
	 *
	 * @param assetTagPersistence the asset tag persistence
	 */
	public void setAssetTagPersistence(
		AssetTagPersistence assetTagPersistence) {

		this.assetTagPersistence = assetTagPersistence;
	}

	/**
	 * Returns the asset tag finder.
	 *
	 * @return the asset tag finder
	 */
	public AssetTagFinder getAssetTagFinder() {
		return assetTagFinder;
	}

	/**
	 * Sets the asset tag finder.
	 *
	 * @param assetTagFinder the asset tag finder
	 */
	public void setAssetTagFinder(AssetTagFinder assetTagFinder) {
		this.assetTagFinder = assetTagFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.asset.kernel.model.AssetEntry",
			assetEntryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.asset.kernel.model.AssetEntry");
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return AssetEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return AssetEntry.class;
	}

	protected String getModelClassName() {
		return AssetEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = assetEntryPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = AssetEntryLocalService.class)
	protected AssetEntryLocalService assetEntryLocalService;

	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;

	@BeanReference(type = AssetEntryFinder.class)
	protected AssetEntryFinder assetEntryFinder;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.GroupLocalService.class
	)
	protected com.liferay.portal.kernel.service.GroupLocalService
		groupLocalService;

	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;

	@BeanReference(type = GroupFinder.class)
	protected GroupFinder groupFinder;

	@BeanReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;

	@BeanReference(
		type = com.liferay.asset.kernel.service.AssetCategoryLocalService.class
	)
	protected com.liferay.asset.kernel.service.AssetCategoryLocalService
		assetCategoryLocalService;

	@BeanReference(type = AssetCategoryPersistence.class)
	protected AssetCategoryPersistence assetCategoryPersistence;

	@BeanReference(type = AssetCategoryFinder.class)
	protected AssetCategoryFinder assetCategoryFinder;

	@BeanReference(
		type = com.liferay.social.kernel.service.SocialActivityCounterLocalService.class
	)
	protected
		com.liferay.social.kernel.service.SocialActivityCounterLocalService
			socialActivityCounterLocalService;

	@BeanReference(type = SocialActivityCounterPersistence.class)
	protected SocialActivityCounterPersistence socialActivityCounterPersistence;

	@BeanReference(type = SocialActivityCounterFinder.class)
	protected SocialActivityCounterFinder socialActivityCounterFinder;

	@BeanReference(
		type = com.liferay.asset.kernel.service.AssetLinkLocalService.class
	)
	protected com.liferay.asset.kernel.service.AssetLinkLocalService
		assetLinkLocalService;

	@BeanReference(type = AssetLinkPersistence.class)
	protected AssetLinkPersistence assetLinkPersistence;

	@BeanReference(type = AssetLinkFinder.class)
	protected AssetLinkFinder assetLinkFinder;

	@BeanReference(
		type = com.liferay.asset.kernel.service.AssetTagLocalService.class
	)
	protected com.liferay.asset.kernel.service.AssetTagLocalService
		assetTagLocalService;

	@BeanReference(type = AssetTagPersistence.class)
	protected AssetTagPersistence assetTagPersistence;

	@BeanReference(type = AssetTagFinder.class)
	protected AssetTagFinder assetTagFinder;

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}