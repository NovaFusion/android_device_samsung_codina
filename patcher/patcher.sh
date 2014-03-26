#!/bin/sh
echo "MaclawStudio patcher for Ace 2 /4.4/\n";

echo "Copying AV patch...\n";
cp av.patch ../../../../frameworks/av
echo "Copying NATIVE patch...\n";
cp native.patch ../../../../frameworks/native
echo "Copying CORE patch...\n";
cp core.patch ../../../../system/core
echo "Copying TELEPHONY patch...\n";
cp telephony.patch ../../../../packages/services/Telephony
echo "Copying RECOVERY patch...\n";
cp recovery.patch ../../../../bootable/recovery
echo "Copying CAMERA patch...\n";
cp camera.patch ../../../../packages/apps/Camera2
echo "Copying BIONIC patch...\n";
cp bionic.patch ../../../../bionic
cd ../../../../frameworks/av
echo "Patching AV...\n";
patch -p1 < av.patch
rm av.patch
cd ../native
echo "Patching NATIVE...\n";
patch -p1 < native.patch
rm native.patch
cd ../../system/core
echo "Patching CORE...\n";
patch -p1 < core.patch
rm core.patch
cd ../../packages/services/Telephony
echo "Patching TELEPHONY...\n";
patch -p1 < telephony.patch
rm telephony.patch
cd ../../../bootable/recovery
echo "Patching RECOVERY...\n";
patch -p1 < recovery.patch
rm recovery.patch
cd ../../packages/apps/Camera2
echo "Patching CAMERA...\n";
patch -p1 < camera.patch
rm camera.patch
cd ../../../bionic
echo "Patching BIONIC...\n";
patch -p1 < bionic.patch
rm bionic.patch
echo "Done. Have a nice day ;-)";
