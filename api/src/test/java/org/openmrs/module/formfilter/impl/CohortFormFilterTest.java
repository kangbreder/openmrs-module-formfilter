/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.formfilter.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Patient;
import org.openmrs.User;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.Verifies;

/**
 * This test validates CohortFormFilter class.
 */
public class CohortFormFilterTest extends BaseModuleContextSensitiveTest {
	
	@Before
	public void loadDataSet() throws Exception {
		executeDataSet("cohortTestDataSyncCreateTest.xml");
	}
	
	/**
	 * Testing the condition to show a form
	 * 
	 * @see {@link CohortFormFilter#shouldDisplayForm(Patient, User)}
	 */
	@Test
	@Verifies(value="should display form when patient is defined in mentioned cohort",method="shouldDisplayForm(Patient, User)")
	public void shouldDisplayForm_shouldDisplayFormWhenPatientIsDefinedInMentionedCohort() {
		CohortFormFilter cohortFormFilter = new CohortFormFilter("cohort=male cohort");
		Patient patient = new Patient(3);
		Assert.assertTrue("Patient is defined in mentioned cohort.", cohortFormFilter.shouldDisplayForm(patient, new User()));
		
	}
	
	/**
	 * Testing the condition to not show a form.
	 * 
	 * @see {@link CohortFormFilter#shouldDisplayForm(Patient, User)}
	 */
	@Test
	@Verifies(value="should not display form when patient is not defined in mentioned cohort",method="shouldDisplayForm(Patient, User)")
	public void shouldDisplayForm_shouldNotDisplayFormWhenPatientIsNotDefinedInMentionedCohort() {
		CohortFormFilter cohortFormFilter = new CohortFormFilter("cohort=male cohort");
		Patient patient = new Patient(2);
		Assert.assertFalse("Patient is not defined in mentioned cohort.",
		    cohortFormFilter.shouldDisplayForm(patient, new User()));
		
	}
	
}
