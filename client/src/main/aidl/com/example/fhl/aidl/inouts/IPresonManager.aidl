// IPresonManager.aidl
package com.example.fhl.aidl.inouts;
import com.example.fhl.aidl.inouts.Preson;
// Declare any non-default types here with import statements

interface IPresonManager {
    List<Preson> getPresons();

    Preson addPresonIn(in Preson preson);
    Preson addPresonOut(out Preson preson);
    Preson addPresonInOut(inout Preson preson);
}
