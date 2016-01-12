package com.Bergie.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

/**
 * Created by Jonathan on 1/12/2016.
 */
@RunWith(Categories.class)
@Categories.IncludeCategory({Major.class})
public interface Major {
}
