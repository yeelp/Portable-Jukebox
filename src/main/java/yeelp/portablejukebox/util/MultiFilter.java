package yeelp.portablejukebox.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

/**
 * A Filter to accept or reject objects of a certain class
 * @author Yeelp
 *
 */
public class MultiFilter
{
	Set<Class> filters;
	
	/**
	 * Build a new MultiFilter with no filters
	 */
	public MultiFilter()
	{
		filters = new HashSet<Class>();
	}
	
	/**
	 * Build a new MultiFilter from the specified classes
	 * @param classes an array of classes to add to this filter
	 */
	public MultiFilter(Class...classes)
	{
		filters = new HashSet<Class>();
		filters.addAll(Arrays.asList(classes));
	}
	
	/**
	 * Add a class to this MultiFilter
	 * @param clazz the class to add
	 * @return true if the filter was changed as a result of the call.
	 * In particular, this returns the result of the underlying call to {@link Set#add(Object)}
	 */
	public boolean addFilter(Class clazz)
	{
		return filters.add(clazz);
	}
	
	/**
	 * Remove a class from this MultiFilter
	 * @param clazz
	 * @return true if the Filter was changed as a result of the call.
	 * In particular, this returns the result of the underlying class to {@link Set#remove(Object)}
	 */
	public boolean remove(Class clazz)
	{
		return filters.remove(clazz);
	}
	
	/**
	 * Check an Object against the filters
	 * @param o the Object to check
	 * @return true if the Object is an instance of one of the classes in the MultiFilter
	 */
	public boolean validate(Object o)
	{
		for(Class clazz : filters)
		{
			if(clazz.isInstance(o))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Classify an object against the filters.
	 * @param o object to classify
	 * @return The first Class instance in the MultiFilter's filters that matched against the object's class, or null, if no such class matched.
	 */
	@Nullable
	public Class classify(Object o)
	{
		for(Class clazz : filters)
		{
			if(clazz.isInstance(o))
			{
				return clazz;
			}
		}
		return null;
	}
}
