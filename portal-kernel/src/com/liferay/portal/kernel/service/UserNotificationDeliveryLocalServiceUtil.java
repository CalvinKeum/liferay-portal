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

package com.liferay.portal.kernel.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;

/**
 * Provides the local service utility for UserNotificationDelivery. This utility wraps
 * <code>com.liferay.portal.service.impl.UserNotificationDeliveryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see UserNotificationDeliveryLocalService
 * @generated
 */
public class UserNotificationDeliveryLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.service.impl.UserNotificationDeliveryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link UserNotificationDeliveryLocalServiceUtil} to access the user notification delivery local service. Add custom service methods to <code>com.liferay.portal.service.impl.UserNotificationDeliveryLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static com.liferay.portal.kernel.model.UserNotificationDelivery
			addUserNotificationDelivery(
				long userId, String portletId, long classNameId,
				int notificationType, int deliveryType, boolean deliver)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addUserNotificationDelivery(
			userId, portletId, classNameId, notificationType, deliveryType,
			deliver);
	}

	/**
	 * Adds the user notification delivery to the database. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationDelivery the user notification delivery
	 * @return the user notification delivery that was added
	 */
	public static com.liferay.portal.kernel.model.UserNotificationDelivery
		addUserNotificationDelivery(
			com.liferay.portal.kernel.model.UserNotificationDelivery
				userNotificationDelivery) {

		return getService().addUserNotificationDelivery(
			userNotificationDelivery);
	}

	/**
	 * Creates a new user notification delivery with the primary key. Does not add the user notification delivery to the database.
	 *
	 * @param userNotificationDeliveryId the primary key for the new user notification delivery
	 * @return the new user notification delivery
	 */
	public static com.liferay.portal.kernel.model.UserNotificationDelivery
		createUserNotificationDelivery(long userNotificationDeliveryId) {

		return getService().createUserNotificationDelivery(
			userNotificationDeliveryId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static void deleteUserNotificationDeliveries(long userId) {
		getService().deleteUserNotificationDeliveries(userId);
	}

	/**
	 * Deletes the user notification delivery with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationDeliveryId the primary key of the user notification delivery
	 * @return the user notification delivery that was removed
	 * @throws PortalException if a user notification delivery with the primary key could not be found
	 */
	public static com.liferay.portal.kernel.model.UserNotificationDelivery
			deleteUserNotificationDelivery(long userNotificationDeliveryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteUserNotificationDelivery(
			userNotificationDeliveryId);
	}

	public static void deleteUserNotificationDelivery(
		long userId, String portletId, long classNameId, int notificationType,
		int deliveryType) {

		getService().deleteUserNotificationDelivery(
			userId, portletId, classNameId, notificationType, deliveryType);
	}

	/**
	 * Deletes the user notification delivery from the database. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationDelivery the user notification delivery
	 * @return the user notification delivery that was removed
	 */
	public static com.liferay.portal.kernel.model.UserNotificationDelivery
		deleteUserNotificationDelivery(
			com.liferay.portal.kernel.model.UserNotificationDelivery
				userNotificationDelivery) {

		return getService().deleteUserNotificationDelivery(
			userNotificationDelivery);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.UserNotificationDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.UserNotificationDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portal.kernel.model.UserNotificationDelivery
		fetchUserNotificationDelivery(long userNotificationDeliveryId) {

		return getService().fetchUserNotificationDelivery(
			userNotificationDeliveryId);
	}

	public static com.liferay.portal.kernel.model.UserNotificationDelivery
		fetchUserNotificationDelivery(
			long userId, String portletId, long classNameId,
			int notificationType, int deliveryType) {

		return getService().fetchUserNotificationDelivery(
			userId, portletId, classNameId, notificationType, deliveryType);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns a range of all the user notification deliveries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.UserNotificationDeliveryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of user notification deliveries
	 * @param end the upper bound of the range of user notification deliveries (not inclusive)
	 * @return the range of user notification deliveries
	 */
	public static java.util.List
		<com.liferay.portal.kernel.model.UserNotificationDelivery>
			getUserNotificationDeliveries(int start, int end) {

		return getService().getUserNotificationDeliveries(start, end);
	}

	/**
	 * Returns the number of user notification deliveries.
	 *
	 * @return the number of user notification deliveries
	 */
	public static int getUserNotificationDeliveriesCount() {
		return getService().getUserNotificationDeliveriesCount();
	}

	/**
	 * Returns the user notification delivery with the primary key.
	 *
	 * @param userNotificationDeliveryId the primary key of the user notification delivery
	 * @return the user notification delivery
	 * @throws PortalException if a user notification delivery with the primary key could not be found
	 */
	public static com.liferay.portal.kernel.model.UserNotificationDelivery
			getUserNotificationDelivery(long userNotificationDeliveryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getUserNotificationDelivery(
			userNotificationDeliveryId);
	}

	public static com.liferay.portal.kernel.model.UserNotificationDelivery
			getUserNotificationDelivery(
				long userId, String portletId, long classNameId,
				int notificationType, int deliveryType, boolean deliver)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getUserNotificationDelivery(
			userId, portletId, classNameId, notificationType, deliveryType,
			deliver);
	}

	public static com.liferay.portal.kernel.model.UserNotificationDelivery
		updateUserNotificationDelivery(
			long userNotificationDeliveryId, boolean deliver) {

		return getService().updateUserNotificationDelivery(
			userNotificationDeliveryId, deliver);
	}

	/**
	 * Updates the user notification delivery in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param userNotificationDelivery the user notification delivery
	 * @return the user notification delivery that was updated
	 */
	public static com.liferay.portal.kernel.model.UserNotificationDelivery
		updateUserNotificationDelivery(
			com.liferay.portal.kernel.model.UserNotificationDelivery
				userNotificationDelivery) {

		return getService().updateUserNotificationDelivery(
			userNotificationDelivery);
	}

	public static UserNotificationDeliveryLocalService getService() {
		if (_service == null) {
			_service =
				(UserNotificationDeliveryLocalService)
					PortalBeanLocatorUtil.locate(
						UserNotificationDeliveryLocalService.class.getName());
		}

		return _service;
	}

	private static UserNotificationDeliveryLocalService _service;

}