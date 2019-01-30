package com.ly.clg.kotlindemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Author: clg48764.
 * Date: 2019/1/30,10:12.
 */
public class SharedPreferencesHelper {
    private static HashMap<String, SharedPreferencesHelper> sExecutorCache = new HashMap();
    private static SharedPreferences sGlobalCompatSP;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences mLocalCompatSP;
    private SharedPreferences.Editor mEditor;

    private SharedPreferencesHelper(Context context, String spName) {
        this.mSharedPreferences = context.getApplicationContext().getSharedPreferences(spName, 0);
        this.mEditor = this.mSharedPreferences.edit();
    }

    public static synchronized SharedPreferencesHelper getInstance(Context context, String spName) {
        SharedPreferencesHelper executor = (SharedPreferencesHelper)sExecutorCache.get(spName);
        if (executor == null) {
            executor = new SharedPreferencesHelper(context, spName);
            sExecutorCache.put(spName, executor);
        }

        return executor;
    }

    public static void enableGlobalCompatSP(Context context, String compatSP) {
        if (context != null && !TextUtils.isEmpty(compatSP)) {
            sGlobalCompatSP = context.getApplicationContext().getSharedPreferences(compatSP, 0);
        }
    }

    public void enableLocalCompatSP(Context context, String compatSP) {
        if (context != null && !TextUtils.isEmpty(compatSP)) {
            this.mLocalCompatSP = context.getApplicationContext().getSharedPreferences(compatSP, 0);
        }
    }

    private SharedPreferences getSharedPreferences(String key) {
        if (this.mSharedPreferences != null && this.mSharedPreferences.contains(key)) {
            return this.mSharedPreferences;
        } else if (this.mLocalCompatSP != null && this.mLocalCompatSP.contains(key)) {
            return this.mLocalCompatSP;
        } else {
            return sGlobalCompatSP != null ? sGlobalCompatSP : this.mSharedPreferences;
        }
    }

    public SharedPreferencesHelper putBoolean(String name, boolean value) {
        if (this.mSharedPreferences != null && !TextUtils.isEmpty(name)) {
            if (this.mEditor == null) {
                this.mEditor = this.mSharedPreferences.edit();
            }

            this.mEditor.putBoolean(name, value);
            return this;
        } else {
            return this;
        }
    }

    public boolean getBoolean(String name, boolean defaultValue) {
        SharedPreferences sp;
        return (sp = this.getSharedPreferences(name)) == null ? defaultValue : sp.getBoolean(name, defaultValue);
    }

    public SharedPreferencesHelper putString(String name, String value) {
        if (this.mSharedPreferences != null && !TextUtils.isEmpty(name)) {
            if (this.mEditor == null) {
                this.mEditor = this.mSharedPreferences.edit();
            }

            this.mEditor.putString(name, value);
            return this;
        } else {
            return this;
        }
    }

    public String getString(String name, String defaultValue) {
        SharedPreferences sp;
        return (sp = this.getSharedPreferences(name)) == null ? defaultValue : sp.getString(name, defaultValue);
    }

    public SharedPreferencesHelper putLong(String name, long value) {
        if (this.mSharedPreferences != null && !TextUtils.isEmpty(name)) {
            if (this.mEditor == null) {
                this.mEditor = this.mSharedPreferences.edit();
            }

            this.mEditor.putLong(name, value);
            return this;
        } else {
            return this;
        }
    }

    public long getLong(String name, long defaultValue) {
        SharedPreferences sp;
        return (sp = this.getSharedPreferences(name)) == null ? defaultValue : sp.getLong(name, defaultValue);
    }

    public SharedPreferencesHelper putInt(String name, int value) {
        if (this.mSharedPreferences != null && !TextUtils.isEmpty(name)) {
            if (this.mEditor == null) {
                this.mEditor = this.mSharedPreferences.edit();
            }

            this.mEditor.putInt(name, value);
            return this;
        } else {
            return this;
        }
    }

    public int getInt(String name, int defaultValue) {
        SharedPreferences sp;
        return (sp = this.getSharedPreferences(name)) == null ? defaultValue : sp.getInt(name, defaultValue);
    }

    public SharedPreferencesHelper putFloat(String name, float value) {
        if (this.mSharedPreferences != null && !TextUtils.isEmpty(name)) {
            if (this.mEditor == null) {
                this.mEditor = this.mSharedPreferences.edit();
            }

            this.mEditor.putFloat(name, value);
            return this;
        } else {
            return this;
        }
    }

    public float getFloat(String name, float defaultValue) {
        SharedPreferences sp;
        return (sp = this.getSharedPreferences(name)) == null ? defaultValue : sp.getFloat(name, defaultValue);
    }

    public SharedPreferencesHelper putSet(String key, Set<String> value) {
        if (this.mSharedPreferences != null && !TextUtils.isEmpty(key)) {
            if (this.mEditor == null) {
                this.mEditor = this.mSharedPreferences.edit();
            }

            this.mEditor.putStringSet(key, value);
            return this;
        } else {
            return this;
        }
    }

    public Set<String> getSet(String key, Set<String> defaultValue) {
        SharedPreferences sp;
        return (sp = this.getSharedPreferences(key)) == null ? defaultValue : sp.getStringSet(key, defaultValue);
    }

    public boolean commit() {
        if (this.mSharedPreferences == null) {
            return false;
        } else {
            if (this.mEditor == null) {
                this.mEditor = this.mSharedPreferences.edit();
            }

            return this.mEditor.commit();
        }
    }

    public void apply() {
        if (this.mSharedPreferences != null) {
            if (this.mEditor == null) {
                this.mEditor = this.mSharedPreferences.edit();
            }

            this.mEditor.apply();
        }
    }

    public SharedPreferencesHelper removeValue(String key) {
        if (TextUtils.isEmpty(key)) {
            return this;
        } else {
            if (sGlobalCompatSP != null && sGlobalCompatSP.contains(key)) {
                sGlobalCompatSP.edit().remove(key).apply();
            }

            if (this.mLocalCompatSP != null && this.mLocalCompatSP.contains(key)) {
                this.mLocalCompatSP.edit().remove(key).apply();
            }

            if (this.mSharedPreferences != null && this.mSharedPreferences.contains(key)) {
                if (this.mEditor == null) {
                    this.mEditor = this.mSharedPreferences.edit();
                }

                this.mEditor.remove(key);
                return this;
            } else {
                return this;
            }
        }
    }

    public SharedPreferencesHelper putStringList(String name, List<String> value) {
        if (this.mSharedPreferences != null && !TextUtils.isEmpty(name) && value != null && !value.isEmpty()) {
            if (this.mEditor == null) {
                this.mEditor = this.mSharedPreferences.edit();
            }

            this.mEditor.remove(name).commit();
            String regularEx = "|";
            String str = "";

            try {
                Object[] objects = value.toArray();
                Object[] var6 = objects;
                int var7 = objects.length;

                for(int var8 = 0; var8 < var7; ++var8) {
                    Object obj = var6[var8];
                    str = str + obj.toString();
                    str = str + "|";
                }

                return this.putString(name, str);
            } catch (Throwable var10) {
                return this;
            }
        } else {
            return this;
        }
    }

    public List<String> getStringList(String name) {
        if (this.mSharedPreferences != null && !TextUtils.isEmpty(name)) {
            String regularEx = "\\|";
            String listString = this.getString(name, (String)null);
            if (listString == null) {
                return new ArrayList();
            } else {
                try {
                    String[] values = listString.split("\\|");
                    List<String> list = new ArrayList();
                    String[] var6 = values;
                    int var7 = values.length;

                    for(int var8 = 0; var8 < var7; ++var8) {
                        String value = var6[var8];
                        if (!TextUtils.isEmpty(value)) {
                            list.add(value);
                        }
                    }

                    return list;
                } catch (Throwable var10) {
                    return new ArrayList();
                }
            }
        } else {
            return new ArrayList();
        }
    }

    public SharedPreferencesHelper clear() {
        if (this.mSharedPreferences == null) {
            return this;
        } else {
            if (this.mEditor == null) {
                this.mEditor = this.mSharedPreferences.edit();
            }

            this.mEditor.clear();
            return this;
        }
    }
}
