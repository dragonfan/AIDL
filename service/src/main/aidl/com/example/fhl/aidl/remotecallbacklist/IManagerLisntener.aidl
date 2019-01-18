// Book.aidl
package com.example.fhl.aidl.remotecallbacklist;
import com.example.fhl.aidl.remotecallbacklist.ILisntener;
// Declare any non-default types here with import statements

interface IManagerLisntener {
    void registerListener(ILisntener listener);
    void unregisterListener(ILisntener listener);
}
