// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "pubgkt",
    platforms: [
        .iOS(.v13),
        .watchOS(.v6),
    ],
    products: [
        .library(name: "pubgkt", targets: ["pubgkt"]),
    ],
    targets: [
        .binaryTarget(
            name: "pubgkt",
            url: "https://github.com/theodorosidmar/pubgkt/releases/download/v1.0.1/pubgkt.xcframework.zip",
            checksum: "b16df214e9ba26e32bf982a78be43d323d02adea86b6c48fa5f35abf9be3b799"
        ),
    ]
)

