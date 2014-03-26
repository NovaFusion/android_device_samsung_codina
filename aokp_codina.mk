# Inherit AOSP device configuration for galaxys2.
$(call inherit-product, device/samsung/codina/full_codina.mk)

# Inherit common product files.
$(call inherit-product, vendor/aokp/configs/common.mk)

# Inherit GSM common stuff
$(call inherit-product, vendor/aokp/configs/gsm.mk)

# Setup device specific product configuration.
PRODUCT_DEVICE := codina
PRODUCT_NAME := aokp_codina
PRODUCT_BRAND := samsung
PRODUCT_MODEL := GT-I8160

PRODUCT_COPY_FILES += \
   vendor/aokp/prebuilt/bootanimation/bootanimation_480_800.zip:system/media/bootanimation-alt.zip
