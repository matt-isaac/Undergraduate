class fiboiter(object):
    fibprev = 0
    fibcurr = 1

    def __init__(self, count):
        self.count = count
        fiboiter.fibprev = 0
        fiboiter.fibcurr = 1

    def __iter__(self):
        return self

    def next():
        if self.count < 0:
            raise StopIteration
        self.count -= 1
        r = fiboiter.fibprev
        fib = fiboiter.fibprev + fiboiter.fibcurr
        fiboiter.fibcurr = fib
        return r


def gen_1234():
    yield 1
    yield 2
    yield 3
    yield 4

for x in gen_1234():
    print x

def gen_custom(low, high):
    for x in xrange(low, high+1):
        yield x

for x in gen_custom(1,10):
    print x


# Grayscale image
ap = argparse.ArgumentParser()
ap.add_argument('-i', '--image', required = True, help = 'Path to image')
args = vars(ap.parse_args())

image = cv2.imread(args['image'])
image_grscl = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
# or...
# image_grscl = cv2.imread(args['image'], 0)
cv2.imshow('Grayscale', image_grscl)
cv2.waitKey()
cv2.destroyAllWindows()


# Split to BGR, merge back together
ap = argparse.ArgumentParser()
ap.add_argument('-i', '--image', required = True, help = 'Path to image')
args = vars(ap.parse_args())

image = cv2.imread(args['image'])
B,G,R = cv2.split(image)
cv2.imshow('Blue', B)
cv2.imshow('Green', G)
cv2.imshow('Red', R)
cv2.waitKey()

merged = cv2.merge([B,G,R])
cv2.imshow('Merged', merged)
cv2.waitKey()
cv2.destroyAllWindows()

# Alternative splitting to BGR, merging back together
image = cv2.imread(args['image'])
B,G,R = cv2.split(image)
cv2.imshow('Blue', cv2.merge([B, zeros, zeros]))
cv2.imshow('Green', cv2.merge([zeros, G, zeros]))
cv2.imshow('Red', cv2.merge([zeros, zeros, R]))
cv2.waitKey()
cv2.destroyAllWindows()

# Split into HSV channels and grayscale
image = cv2.imread(args['image'])
grscl = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
hsv = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
cv2.imshow('Hue', hsv[:,:,0])
cv2.imshow('Saturation', hsv[:,:,1])
cv2.imshow('Value', hsv[:,:,2])
cv2.waitKey()
cv2.destroyAllWindows()

# Rotate image around center by user-specified degrees
ap = argparse.ArgumentParser()
ap.add_argument('-i', '--image', required = True, help = 'Path to image')
ap.add_argument('-d', '--degrees', required = True, help = 'Degrees to rotate')
args = vars(ap.parse_args())

image = cv2.imread(args['image'])
h,w,nc = image.shape()
center = (h/2, w/2)
RM = cv2.getRotationMatrix(center, args['degrees'], 1.0)
rotated_image = cv2.warpAffine(image, RM180, (w,h))

# Crop image to user-specified area
ap = argparse.ArgumentParser()
ap.add_argument('-i', '--image', required = True, help = 'Path to image')
ap.add_argument('-sr', '--startrow', required = True, help = 'Beginning row')
ap.add_argument('-er', '--endrow', required = True, help = 'Ending row')
ap.add_argument('-sc', '--startcol', required = True, help = 'Beginning column')
ap.add_argument('-ec', '--endcol', required = True, help = 'Ending column')
args = vars(ap.parse_args())

image = cv2.imread(args['image'])
h,w,nc = image.shape()
if(args['startrow'] < 0 or args['endrow'] > w):
    print 'Start and/or end row beyond image boundaries'
else if(args['startcol'] < 0 or args['endcol'] > h):
    print 'Start and/or end columns beyone image boundaries'
else:
    cropped = image[int(sr):int(er), int(sc):int(ec)]
    cv2.imshow('Cropped', cropped)
    cv2.waitKey()
    del image
    del cropped

# Resize image
ap = argparse.ArgumentParser()
ap.add_argument('-i', '--image', required = True, help = 'Path to image')
ap.add_argument('-w', '--width', required = True, help = 'New width')
args = vars(ap.parse_args())

image = cv2.imread(args['image'])
new_width = int(args['width'])
width_ratio = fload(new_width) / w
dim = (new_width, int(h*width_ratio))
resized = cv2.resize(image, dim, interpolation=cv2.INTER_AREA)
cv2.imshow(resized)
cv2.waitKey()
del resized

# Max BGR filter
import numpy as np
import cv2

def max_rgb_filter(image):
    B,G,R = cv2.split(image)

    M = np.maximum(np.maximum(R,G,B))
    R[R<M] = 0
    G[G<M] = 0
    B[B<M] = 0

    return cv2.merge([B,G,R])

# Blurring Images
ap = argparse.ArgumentParser()
ap.add_argument('-i', '--image', required = True, help = 'Path to image')
args = vars(ap.parse_args())

image = cv2.imread(args['image'])
cv2.imshow('Original Image', image)

kernel_3x3 = np.ones((3,3), np.float32)/9 # mean
blurred = cv2.filter2D(image, -1, kernel_3x3)

# or...

blurred_mean = cv2.blur(image, (3,3)) # mean
blurred_gauss = cv2.GaussianBlur(image, (7,7)) # gaussian
blurred_medain = cv2.medianBlur(image, (3,3)) # median
blurred_bilateral = cv2.bilateralFilter(image, 9, 75, 75)

# Erode and Dilate image

er_img = cv2.erode(image, (5,5))
dil_image = cv2.dilate(image, (5,5))

# PIL images
import Image
# create new image: Image.new(mode, size [,color])
# mode: '1' - binary, 'L' - grayscale, 'RGB' - color
# size is a tuple - (width, height)
# color is optional - defaults to black

# Edge Detection (Canny)
image = cv2.imread(args['image'])
gray_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
image_edges = cv2.Canny(gray_image, 100, 200, apertureSize = 3, L2gradient = TRUE)

# Image Histograms
# code: cv2.calcHist(image, channels, mask, histSize, ranges)
# ex: cv2.calcHist(image, [0,1,2], None, [8,8,8], [0,256])
# [8,8,8] means 8 bins for each channel

# 256-bin histograms for separated colors (RGB). 
def hist_256_32(image_path):
    image = cv2.imread(image_path)
    B_hist = cv2.calcHist([image], [0], None, [256], [0,256])
    G_hist = cv2.calcHist([image], [1], None, [256], [0,256])
    R_hist = cv2.calcHist([image], [2], None, [256], [0,256])
    
    image_gr = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    gr_hist = cv2.calcHist(image_gr, [0], None, [32], [0,256])

# 256 and 32 bin histograms for HSV
image = cv2.imread(args['image'])
image_hsv = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
H,S,V = split(image_hsv)
H_hist = cv2.calcHist([image], [0], None, [180], [0,180])
S_hist = cv2.calcHist([image], [1], None, [256], [0,256])
V_hist = cv2.calcHist([image], [2], None, [256], [0,256])
# plot histograms ^ in matplotlib
fig1 = plt.figure(1)
fig1.suptitle('HSV Histograms')
plt.xlim([0,256])
plt.xlabel('Bins')
plt.ylabel('Pixel Count')
plt.subplot(311)
plt.plot(H_hist, color='b')
plt.subplot(312)
plt.plot(S_hist, color='g')
plt.subplot(313)
plt.plot(V_hist, color='r')

# Turning histograms into feature vectors
# Normalize and flatten: cv2.normalize(input_hist, dest_hist).flatten()
ap = argparse.ArgumentParser()
ap.add_argument('-i', '--image', required = True, help = 'Path to image')
args = vars(ap.parse_args())

image = cv2.imread(args['image'])
image_rgb = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
image_hist = cv2.calcHist([image], [0,1,2], None, [8,8,8], [0,256, 0,256, 0,256 ])
norm_flat_hist = cv2.normalize(image_hist, nf_hist).flatten()

# Match metrics: Correlation, Chi Squared, Intersection, Bhattacharyya
ap = argparse.ArgumentParser()
ap.add_argument('-i1', '--image_1', required = True, help = 'Path to image 1')
ap.add_argument('-i2', '--image_2', required = True, help = 'Path to image 2')
args = vars(ap.parse_args())

image1 = cv2.imread(args['image_1'])
image2 = cv2.imread(args['image_2'])

im1_hist = cv2.calcHist([image1], [0,1,2], None, [8,8,8], [0,256, 0,256, 0,256])
im2_hist = cv2.calcHist([image2], [0,1,2], None, [8,8,8], [0,256, 0,256, 0,256])

norm_hist1 = cv2.normalize(im1_hist, im1_hist).flatten()
norm_hist2 = cv2.normalize(im2_hist, im2_hist).flatten()

correlation = cv2.compareHist(norm_hist1, norm_hist2, cv2.HISTCMP_CORREL)
chisq = cv2.compareHist(norm_hist1, norm_hist2, cv2.HISTCMP_CHISQR)
intersect = cv2.compareHist(norm_hist1, norm_hist2, cv2.HISTCMP_INTERSECT)
bhatta = cv2.compareHist(norm_hist1, norm_hist2, cv2.HISTCMP_BHATTACHARYYA)

print 'Correlation: ' + str(correlation)
print 'Chi Squared: ' + str(chisq)
print 'Intersection: ' + str(intersect)
print 'Bhattacharyya: ' + str(bhatta)











