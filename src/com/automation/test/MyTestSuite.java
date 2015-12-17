package com.automation.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.framework.datadriven.snapdeal.scripts.SnapDealPurchase;

@RunWith(Suite.class)
@SuiteClasses({ AlertTest.class, ControlMouseClick.class, CricketTest.class,
		ElementIsDisplayedOrHidden.class, FacebookTest.class,
		FlipkartTest.class, GmailTest.class, GoogleDropdowncount.class,
		IsElementPresent.class, IsTextPresent.class, PaytmTestCase.class,
		RadioButtonTest.class, SelectedLabel.class, Snapdeal.class,
		TooltipTest.class,SnapDealPurchase.class })
public class MyTestSuite {

}
