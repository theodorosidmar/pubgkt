// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "PubgKt",
    platforms: [
        .iOS(.v13),
        .watchOS(.v6),
    ],
    products: [
        .library(name: "PubgKt", targets: ["PubgKt"]),
    ],
    targets: [
        .binaryTarget(
            name: "PubgKt",
            url: "https://github.com/theodorosidmar/pubgkt/releases/download/v1.0.1/PubgKt.xcframework.zip",
            checksum: "17e6ab7b707e6ce889f4703a998083226ddf784632206a3ad8ecf82d7573d41d"
        ),
    ]
)
