let path = require('path');
let webpack = require('webpack');

const config = {
    entry: {
        'entry':'./src/entry.js'
    },
    output: {
        path: path.resolve(__dirname, '../webapp/resources/js/dist'),
        filename: "[name].js"
    },
    plugins: [
        new webpack.ProvidePlugin({
            $: "jQuery",
            jQuery: "jQuery",
            "window.jQuery": 'jQuery',
            Popper: ['popper.js', 'default'],
            "window.Popper": ['popper.js', 'default']
        })
    ],
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['env']
                    }
                }
            },
            {
                test: /\.css$/,
                use: [ 'style-loader', 'css-loader' ]
            }
        ]
    }
};

module.exports = config;