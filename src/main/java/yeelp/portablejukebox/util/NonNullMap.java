package yeelp.portablejukebox.util;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.util.NonNullList;

/**
 * A Simple map that doesn't allow null Values.
 * @author Yeelp
 *
 * @param <Key> the type of keys of the map
 * @param <Value> the type of values stored in the map
 */
public class NonNullMap<Key, Value> extends AbstractMap<Key, Value> implements Map<Key, Value>
{
	private Value defaultVal;
	private List<Key> keys;
	private List<Value> vals;
	
	private NonNullMap()
	{
		throw new UnsupportedOperationException("A default value must be specifed for the NonNullMap");
	}
	
	/**
	 * Create a new NonNullMap
	 * @param defaultVal the default value stored in the map. The NonNullMap will fall back to this value whenever it encounters a null Value.
	 */
	public NonNullMap(Value defaultVal)
	{
		this.defaultVal = defaultVal;
		keys = new ArrayList<Key>();
		vals = NonNullList.<Value>create();
	}
	
	/**
	 * Create a new NonNullMap initialized with the specified keys and default value
	 * @param keys the keys to use when initializing the map
	 * @param defaultVal the default value to initialize the map with. The NonNullMap will use this whenever it encounters null.
	 */
	public NonNullMap(Value defaultVal, Key...keys)
	{
		this.keys = Arrays.asList(keys);
		vals = NonNullList.<Value>create();
		for(int i = 0;  i < keys.length; i++)
		{
			vals.add(defaultVal);
		}
		this.defaultVal = defaultVal;
	}
	
	@Override
	public int size() 
	{
		return keys.size();
	}
	@Override
	public boolean isEmpty() 
	{
		return keys.size() == 0;
	}
	@Override
	public boolean containsKey(Object key) 
	{
		for(Key k : keys)
		{
			if(k.equals(key))
			{
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean containsValue(Object value) 
	{
		for(Value v : vals)
		{
			if(v.equals(value))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * {@inheritDoc}
	 * 
	 * 
	 * <p> For the NonNullMap, if the key has no mapping, the default value is returned instead.
	 */
	public Value get(Object key) 
	{
		int index = keys.indexOf(key);
		return index == -1 ? this.defaultVal : vals.get(index);
	}
	
	@Override
	public Value put(Key key, Value value) 
	{
		int index = keys.indexOf(key);
		if(index == -1)
		{
			keys.add(key);
			vals.add(value);
			return null;
		}
		else
		{
			Value val = vals.remove(index);
			vals.add(index, value);
			return val;
		}
	}
	@Override
	public Value remove(Object key) 
	{
		int index = keys.indexOf(key);
		if(index == -1)
		{
			return null;
		}
		else
		{
			Value val = vals.remove(index);
			vals.add(index, defaultVal);
			return val;
		}
	}
	@Override
	public void putAll(Map<? extends Key, ? extends Value> m) 
	{
		for(Entry<? extends Key, ? extends Value> entry : m.entrySet())
		{
			put(entry.getKey(), entry.getValue());
		}
	}
	@Override
	public void clear() 
	{
		keys = new ArrayList<Key>();
		vals = NonNullList.<Value>create();
	}
	@Override
	public Set<Key> keySet() 
	{
		return super.keySet();
	}
	@Override
	public Collection<Value> values() 
	{
		return super.values();
	}
	@Override
	public Set<Entry<Key, Value>> entrySet() 
	{
		Set<Entry<Key, Value>> es = new HashSet<Entry<Key, Value>>();
		for(Key k : keys)
		{
			es.add(new SimpleEntry(k, get(k)));
		}
		return es;
	}
}
